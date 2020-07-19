package com.tushar.eventstores.example.kanban.board.event;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBoardEvent extends BoardEvent<String> {

	private String title;

	private LocalDate updatedAt;

	private String updatedBy;

	public UpdateBoardEvent(String id, String title, LocalDate updatedAt, String updatedBy) {
		super(id);
		this.title = title;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
	}

}
