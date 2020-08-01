package com.tushar.eventstores.example.kanban.task.command;

import com.tushar.eventstores.example.kanban.task.aggregrate.TaskType;

import lombok.Data;

@Data
public class CompletedTaskCommand extends TaskCommand<String> {

	private String description;
	private TaskType taskType;
	private String boardTitle;

	public CompletedTaskCommand(String id, String description, TaskType taskType, String boardTitle) {
		super(id);
		this.description = description;
		this.taskType = taskType;
		this.boardTitle = boardTitle;
	}

}
