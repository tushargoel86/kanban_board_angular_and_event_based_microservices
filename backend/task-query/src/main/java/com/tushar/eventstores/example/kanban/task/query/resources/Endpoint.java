package com.tushar.eventstores.example.kanban.task.query.resources;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.eventstores.example.kanban.task.aggregrate.TaskType;
import com.tushar.eventstores.example.kanban.task.query.model.TaskQuery;
import com.tushar.eventstores.example.kanban.task.query.request.TaskRequest;
import com.tushar.eventstores.example.kanban.task.query.service.TaskQueryService;

@RestController
@RequestMapping("/tasks")
public class Endpoint {

	private TaskQueryService taskService;

	public Endpoint(TaskQueryService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("/{boardTitle}")
	@CrossOrigin(origins = "https://localhost:4200")
	public List<TaskQuery> tasks(@PathVariable("boardTitle") String boardTitle) {
		return this.taskService.findAllTaskFor(boardTitle);
	}

//	@DeleteMapping("/{id}")
//	@CrossOrigin(origins = "https://localhost:4200")
//	public void deleteTask(@PathVariable String id) {
//		this.taskService.deleteTask(id);
//	}
//
//	@PutMapping("/{id}")
//	@CrossOrigin(origins = "https://localhost:4200")
//	public TaskQuery updateTask(@PathVariable String id, @RequestBody TaskRequest taskRequest) {
//		return this.taskService.updateTask(
//				new TaskQuery(id, taskRequest.getBoardTitle(), taskRequest.getDescription(), 
//						TaskType.valueOf(taskRequest.getTaskType().toUpperCase())));
//	}
//	
	 

}
