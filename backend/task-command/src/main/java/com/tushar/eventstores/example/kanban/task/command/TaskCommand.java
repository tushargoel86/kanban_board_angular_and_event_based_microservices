package com.tushar.eventstores.example.kanban.task.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class TaskCommand<T> {

	@TargetAggregateIdentifier
	public final T id;
	
}
