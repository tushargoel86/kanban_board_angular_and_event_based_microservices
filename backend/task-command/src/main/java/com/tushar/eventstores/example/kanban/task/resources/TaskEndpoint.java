package com.tushar.eventstores.example.kanban.task.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.eventstores.example.kanban.task.dto.BasicTaskDTO;
import com.tushar.eventstores.example.kanban.task.response.Response;
import com.tushar.eventstores.example.kanban.task.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskEndpoint {

	private TaskService taskService;

	public TaskEndpoint(TaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping
	@CrossOrigin(origins = "https://localhost:4200")
	public CompletableFuture<ResponseEntity<Response>> saveTask(@Valid @RequestBody BasicTaskDTO dto) {
		return taskService.createTask(dto)
						  .thenApply(id -> new ResponseEntity<Response>(new Response(id), HttpStatus.CREATED))
						  .exceptionally(msg -> new ResponseEntity<Response>(new Response("taks already available"), HttpStatus.CONFLICT));
	}


	@PutMapping("/{id}")
	@CrossOrigin(origins = "https://localhost:4200")
	public CompletableFuture<ResponseEntity<Response>> updateTask(@PathVariable("id") String taskId, @Valid @RequestBody BasicTaskDTO dto) {
		return taskService.updateTask(taskId, dto)
						  .thenApply(id -> new ResponseEntity<Response>(new Response(id), HttpStatus.ACCEPTED))
						  .exceptionally(msg -> new ResponseEntity<Response>(new Response(msg.getMessage()), HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	@CrossOrigin(origins = "https://localhost:4200")
	public CompletableFuture<ResponseEntity<Response>> deleteTask(@PathVariable String id) {
		return taskService.deleteTask(id)
						  .thenApply(taskId -> new ResponseEntity<Response>(new Response(taskId != null ? taskId : id), HttpStatus.OK))
						  .exceptionally(msg -> new ResponseEntity<Response>(new Response(msg.getMessage()), HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/{id}/todo")
	@CrossOrigin(origins = "https://localhost:4200")
	public CompletableFuture<ResponseEntity<Response>> todoTask(@PathVariable("id") String taskId, @Valid @RequestBody BasicTaskDTO dto) {
		return taskService.todotask(taskId, dto)
						  .thenApply(id -> new ResponseEntity<Response>(new Response(id), HttpStatus.ACCEPTED))
						  .exceptionally(msg -> new ResponseEntity<Response>(new Response(msg.getMessage()), HttpStatus.NOT_FOUND));
	}
 
	@PostMapping("/{id}/inprogress")
	@CrossOrigin(origins = "https://localhost:4200")
	public CompletableFuture<ResponseEntity<Response>> inprogressTask(@PathVariable("id") String taskId, @Valid @RequestBody BasicTaskDTO dto) {
		return taskService.inprogresstask(taskId, dto)
						  .thenApply(id -> new ResponseEntity<Response>(new Response(id), HttpStatus.ACCEPTED))
						  .exceptionally(msg -> new ResponseEntity<Response>(new Response(msg.getMessage()), HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/{id}/complete")
	@CrossOrigin(origins = "https://localhost:4200")
	public CompletableFuture<ResponseEntity<Response>> completedTask(@PathVariable("id") String taskId, @Valid @RequestBody BasicTaskDTO dto) {
		return taskService.completedtask(taskId, dto)
						  .thenApply(id -> new ResponseEntity<Response>(new Response(id), HttpStatus.ACCEPTED))
						  .exceptionally(msg -> new ResponseEntity<Response>(new Response(msg.getMessage()), HttpStatus.NOT_FOUND));
	}
	

	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
