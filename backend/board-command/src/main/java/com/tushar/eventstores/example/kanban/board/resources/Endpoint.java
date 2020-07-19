package com.tushar.eventstores.example.kanban.board.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.axonframework.axonserver.connector.command.AxonServerRemoteCommandHandlingException;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.eventstores.example.kanban.board.dto.CreateBoardDTO;
import com.tushar.eventstores.example.kanban.board.dto.UpdateBoardDTO;
import com.tushar.eventstores.example.kanban.board.exception.BoardAlreadyAvailableException;
import com.tushar.eventstores.example.kanban.board.response.Response;
import com.tushar.eventstores.example.kanban.board.service.BoardService;

@RestController
@RequestMapping("/boards")
public class Endpoint {

	private BoardService boardService;

	public Endpoint(BoardService boardService) {
		this.boardService = boardService;
	}

	@PostMapping("/")
	@CrossOrigin(origins = "https://localhost:4200")
	public CompletableFuture<ResponseEntity<Response>> createBoard(@Valid @RequestBody CreateBoardDTO createBoardDTO) {
		return boardService.createBoard(createBoardDTO)
				.thenApply(id -> new ResponseEntity<Response>(new Response(id), HttpStatus.CREATED))
				.exceptionally(ex -> new ResponseEntity<Response>(new Response("Board title already available"),
						HttpStatus.CONFLICT));
	}

	@PutMapping("/")
	@CrossOrigin(origins = "https://localhost:4200")
	public CompletableFuture<String> updateBoard(@Valid @RequestBody UpdateBoardDTO updateBoardDTO) {
		return boardService.updateBoard(updateBoardDTO);
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
