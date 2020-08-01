package com.tushar.eventstores.example.kanban.task.command;

import lombok.Data;

@Data
public class DeleteTaskCommand extends TaskCommand<String> {
	
	public DeleteTaskCommand(String id) {
		super(id);
	}
	
	 
	
}
