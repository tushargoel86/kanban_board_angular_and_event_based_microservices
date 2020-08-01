package com.tushar.eventstores.example.kanban.task.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicTaskDTO {

	@NotBlank(message = "board title is required")
	private String boardTitle;
	
	@NotBlank(message = "task description is required")
	private String description;
	
	@NotBlank(message = "task type is required")
	private String taskType;
}
