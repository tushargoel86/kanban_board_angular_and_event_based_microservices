package com.tushar.eventstores.example.kanban.board.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class BoardEvent<T> {

	@TargetAggregateIdentifier
	private final T id;
}
