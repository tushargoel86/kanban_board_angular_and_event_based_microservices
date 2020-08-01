package com.tushar.eventstores.example.kanban.task.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.tushar.eventstores.example.kanban.task.aggregrate.TaskType;
import com.tushar.eventstores.example.kanban.task.command.CompletedTaskCommand;
import com.tushar.eventstores.example.kanban.task.command.CreateTaskCommand;
import com.tushar.eventstores.example.kanban.task.command.DeleteTaskCommand;
import com.tushar.eventstores.example.kanban.task.command.InprogrssTaskCommand;
import com.tushar.eventstores.example.kanban.task.command.ToDoTaskCommand;
import com.tushar.eventstores.example.kanban.task.command.UpdateTaskCommand;
import com.tushar.eventstores.example.kanban.task.dto.BasicTaskDTO;

@Service
public class TaskService {

	private CommandGateway commandGateway;

	public TaskService(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	public CompletableFuture<String> createTask(BasicTaskDTO dto) {
		TaskType type = TaskType.valueOf(dto.getTaskType().toUpperCase());
		return commandGateway.send(new CreateTaskCommand(UUID.randomUUID().toString(), dto.getDescription(), type, dto.getBoardTitle()));
	}
	
	public CompletableFuture<String> updateTask(String taskId, BasicTaskDTO dto) {
		TaskType type = TaskType.valueOf(dto.getTaskType().toUpperCase());
		return commandGateway.send(new UpdateTaskCommand(taskId, dto.getDescription(), type, dto.getBoardTitle()));
	}

	public CompletableFuture<String> deleteTask(String taskId) {
		return commandGateway.send(new DeleteTaskCommand(taskId));
	}
	
	public CompletableFuture<String> todotask(String taskId, BasicTaskDTO dto) {
		TaskType type = TaskType.valueOf(dto.getTaskType().toUpperCase());
		return commandGateway.send(new ToDoTaskCommand(taskId, dto.getDescription(), type, dto.getBoardTitle()));
	}
	
	public CompletableFuture<String> inprogresstask(String taskId, BasicTaskDTO dto) {
		TaskType type = TaskType.valueOf(dto.getTaskType().toUpperCase());
		return commandGateway.send(new InprogrssTaskCommand(taskId, dto.getDescription(), type, dto.getBoardTitle()));
	}
	
	public CompletableFuture<String> completedtask(String taskId, BasicTaskDTO dto) {
		TaskType type = TaskType.valueOf(dto.getTaskType().toUpperCase());
		return commandGateway.send(new CompletedTaskCommand(taskId, dto.getDescription(), type, dto.getBoardTitle()));
	}
	
	
}
