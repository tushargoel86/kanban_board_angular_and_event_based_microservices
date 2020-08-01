package com.tushar.eventstores.example.kanban.task.event;

import com.tushar.eventstores.example.kanban.task.aggregrate.TaskType;

import lombok.Data;

@Data
public class InprogrssTaskEvent extends TaskEvent<String> {

	private String description;
	private TaskType taskType;
	private String boardTitle;

	public InprogrssTaskEvent(String id, String description, TaskType taskType, String boardTitle) {
		super(id);
		this.description = description;
		this.taskType = taskType;
		this.boardTitle = boardTitle;
	}

}
