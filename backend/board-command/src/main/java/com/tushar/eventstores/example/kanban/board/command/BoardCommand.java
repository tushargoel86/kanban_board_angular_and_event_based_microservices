package com.tushar.eventstores.example.kanban.board.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class BoardCommand<T> {

	@TargetAggregateIdentifier
	public final T title;
	
}
