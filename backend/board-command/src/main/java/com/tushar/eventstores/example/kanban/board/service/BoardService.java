package com.tushar.eventstores.example.kanban.board.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import com.tushar.eventstores.example.kanban.board.command.CreateBoardCommand;
import com.tushar.eventstores.example.kanban.board.command.UpdateBoardCommand;
import com.tushar.eventstores.example.kanban.board.dto.CreateBoardDTO;
import com.tushar.eventstores.example.kanban.board.dto.UpdateBoardDTO;

@Service
public class BoardService {

	private final CommandGateway commandGateway;
	private final EventStore eventStore;

	public BoardService(CommandGateway commandGateway, EventStore eventStore) {
		this.commandGateway = commandGateway;
		this.eventStore = eventStore;
	}

	public CompletableFuture<String> createBoard(CreateBoardDTO dto) {
		return commandGateway.send(new CreateBoardCommand(UUID.randomUUID().toString(), dto.getTitle(),
				dto.getCreatedAt(), dto.getCreatedBy()));
	}
	
	public CompletableFuture<String> updateBoard(UpdateBoardDTO dto) {
		return commandGateway.send(new UpdateBoardCommand(dto.getId(), dto.getTitle(),
				dto.getUpdatedAt(), dto.getUpdatedBy()));
	}


}
