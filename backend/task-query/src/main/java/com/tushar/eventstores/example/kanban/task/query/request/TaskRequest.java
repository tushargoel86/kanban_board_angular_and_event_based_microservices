package com.tushar.eventstores.example.kanban.task.query.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskRequest {

	@NotBlank(message = "board title is required")
	private String boardTitle;
	@NotBlank(message = "description is required")
	private String description;
	@NotBlank(message = "task type is required")
	private String taskType;
}
