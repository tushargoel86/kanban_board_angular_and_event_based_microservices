package com.tushar.eventstores.example.kanban.task.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TaskEvent<T> {

	@TargetAggregateIdentifier
	private final T id;
}
