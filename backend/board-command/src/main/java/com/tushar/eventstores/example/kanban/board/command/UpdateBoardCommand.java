package com.tushar.eventstores.example.kanban.board.command;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBoardCommand extends BoardCommand<String> {

	private String title;

	private LocalDate updatedAt;

	private String updatedBy;

	public UpdateBoardCommand(String id, String title, LocalDate updatedAt, String updatedBy) {
		super(id);
		this.title = title;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
	}

}
