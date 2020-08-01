package com.tushar.eventstores.example.kanban.board.service;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.tushar.eventstores.example.kanban.board.command.CreateBoardCommand;
import com.tushar.eventstores.example.kanban.board.command.DeleteBoardCommand;
import com.tushar.eventstores.example.kanban.board.command.UpdateBoardCommand;
import com.tushar.eventstores.example.kanban.board.dto.CreateBoardDTO;

@Service
public class BoardService {

	private final CommandGateway commandGateway;

	public BoardService(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	public CompletableFuture<String> createBoard(CreateBoardDTO dto) {
		return commandGateway.send(new CreateBoardCommand(dto.getTitle(), dto.getCreatedAt(), dto.getCreatedBy()));
	}

	public CompletableFuture<String> updateBoard(UpdateBoardCommand cmd) {
		return commandGateway.send(cmd);
	}

	public CompletableFuture<String> deleteBoard(String title) {
		return commandGateway.send(new DeleteBoardCommand(title));
	}

}
