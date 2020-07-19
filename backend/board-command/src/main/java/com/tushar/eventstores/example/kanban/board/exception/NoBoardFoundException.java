package com.tushar.eventstores.example.kanban.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "board is required")
public class NoBoardFoundException extends RuntimeException {

}
