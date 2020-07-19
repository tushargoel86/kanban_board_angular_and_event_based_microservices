package com.tushar.eventstores.example.kanban.board.command;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBoardCommand extends BoardCommand<String> {

	private String title;

	private LocalDate createdAt;

	private String createdBy;

	public CreateBoardCommand(String id, String title, LocalDate createdAt, String createdBy) {
		super(id);
		this.title = title;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}
}
