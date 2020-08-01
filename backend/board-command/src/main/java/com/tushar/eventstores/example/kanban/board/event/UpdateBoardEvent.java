package com.tushar.eventstores.example.kanban.board.event;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBoardEvent extends BoardEvent<String> {

	private LocalDate updatedAt;

	private String updatedBy;

	public UpdateBoardEvent(String title, LocalDate updatedAt, String updatedBy) {
		super(title);
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
	}

}
