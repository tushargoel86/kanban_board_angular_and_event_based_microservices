package com.tushar.eventstores.example.kanban.board.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBoardDTO {

	@NotBlank(message = "title is required")
	@Size(min = 4, max=15, message = "title must be between {min} and {max}")
	private String title;

	@NotNull(message = "date is required in dd-mm-yyyy format")
	@JsonProperty("createdAt")
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate createdAt;

	@NotBlank(message = "name is required")
	@Size(min = 4, max=15, message = "name must be between {min} and {max}")
	private String createdBy;
}
