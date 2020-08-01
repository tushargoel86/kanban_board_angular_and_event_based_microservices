package com.tushar.eventstores.example.kanban.task.command;

import com.tushar.eventstores.example.kanban.task.aggregrate.TaskType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskCommand extends TaskCommand<String> {

	private String description;
	private TaskType taskType;
	private String boardTitle;

	public CreateTaskCommand(String id, String description, TaskType taskType, String boardTitle) {
		super(id);
		this.description = description;
		this.taskType = taskType;
		this.boardTitle = boardTitle;
	}

}
