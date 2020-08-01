package com.tushar.eventstores.example.kanban.task.query.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeTaskStatusRequest {
	private String boardTitle;
}
