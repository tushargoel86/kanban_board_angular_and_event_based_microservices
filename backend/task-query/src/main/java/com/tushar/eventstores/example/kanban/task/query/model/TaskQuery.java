package com.tushar.eventstores.example.kanban.task.query.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.tushar.eventstores.example.kanban.task.aggregrate.TaskType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskQuery {
	@Id
	private String taskId;
	private String boardTitle;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private TaskType taskType;

}
