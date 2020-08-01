package com.tushar.eventstores.example.kanban.board.event;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBoardEvent extends BoardEvent<String>{
	
	private LocalDate createdAt;
	
	private String createdBy;

	public CreateBoardEvent(String title, LocalDate createdAt, String createdBy) {
		super(title);
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}
	
}
