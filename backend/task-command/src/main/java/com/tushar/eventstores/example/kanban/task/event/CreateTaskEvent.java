package com.tushar.eventstores.example.kanban.task.event;

import com.tushar.eventstores.example.kanban.task.aggregrate.TaskType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskEvent extends TaskEvent<String> {
	
	private String description;
	private TaskType taskType;
	private String boardTitle;
	
	public CreateTaskEvent(String id, String description, TaskType taskType, String boardTitle) {
		super(id);
		this.description = description;
		this.taskType = taskType;
		this.boardTitle = boardTitle;
	}

	 
}
