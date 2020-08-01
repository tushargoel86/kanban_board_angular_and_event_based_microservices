package com.tushar.eventstores.example.kanban.board.event;

import lombok.Data;

@Data
public class DeleteBoardEvent extends BoardEvent<String> {
	
	public DeleteBoardEvent(String title) {
		super(title);
	}

}
