package com.tushar.eventstores.example.kanban.task.event;

import lombok.Data;

@Data
public class DeleteTaskEvent extends TaskEvent<String> {
	
	public DeleteTaskEvent(String id) {
		super(id);
	}

}
