package com.tushar.eventstores.example.kanban.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason="board already available")
public class BoardAlreadyAvailableException extends RuntimeException {

	public BoardAlreadyAvailableException(String arg0) {
		super(arg0);
	}

	
}
