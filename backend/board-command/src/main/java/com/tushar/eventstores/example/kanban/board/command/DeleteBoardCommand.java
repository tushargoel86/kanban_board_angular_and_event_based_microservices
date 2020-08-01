package com.tushar.eventstores.example.kanban.board.command;

import lombok.Data;

@Data
public class DeleteBoardCommand extends BoardCommand<String> {

	public DeleteBoardCommand(String title) {
		super(title);
	}
	
	
	
}
