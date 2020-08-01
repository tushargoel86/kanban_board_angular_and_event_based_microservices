package com.tushar.eventstores.example.kanban.task.query.model;

import javax.persistence.EntityManager;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.tushar.eventstores.example.kanban.task.event.CompletedTaskEvent;
import com.tushar.eventstores.example.kanban.task.event.CreateTaskEvent;
import com.tushar.eventstores.example.kanban.task.event.DeleteTaskEvent;
import com.tushar.eventstores.example.kanban.task.event.InprogrssTaskEvent;
import com.tushar.eventstores.example.kanban.task.event.ToDoTaskEvent;
import com.tushar.eventstores.example.kanban.task.event.UpdateTaskEvent;
import com.tushar.eventstores.example.kanban.task.query.repository.TaskRepository;

@Component
public class TaskQueryEntityManager {

	private TaskRepository taskRepository;
	private EntityManager manager;
	
	public TaskQueryEntityManager(TaskRepository taskRepository, EntityManager manager) {
		this.taskRepository = taskRepository;
		this.manager = manager;
	}

	@EventHandler
	void on (CreateTaskEvent event) {
		String id = event.getId();
		TaskQuery task = new TaskQuery(id, event.getBoardTitle(), event.getDescription(), event.getTaskType());
		taskRepository.save(task);
	}

	@EventHandler
	void on (UpdateTaskEvent event) {
		String id = event.getId();
		TaskQuery task = new TaskQuery(id, event.getBoardTitle(), event.getDescription(), event.getTaskType());
		taskRepository.save(task);
	}
	
	@EventHandler
	void on (DeleteTaskEvent event) {
		taskRepository.deleteById(event.getId());
	}
	
	@EventHandler
	void on (ToDoTaskEvent event) {
		String id = event.getId();
		TaskQuery task = new TaskQuery(id, event.getBoardTitle(), event.getDescription(), event.getTaskType());
		taskRepository.save(task);
	}
	
	@EventHandler
	void on (InprogrssTaskEvent event) {
		String id = event.getId();
		TaskQuery task = new TaskQuery(id, event.getBoardTitle(), event.getDescription(), event.getTaskType());
		taskRepository.save(task);
	}
	
	@EventHandler
	void on (CompletedTaskEvent event) {
		String id = event.getId();
		TaskQuery task = new TaskQuery(id, event.getBoardTitle(), event.getDescription(), event.getTaskType());
		taskRepository.save(task);
	}

}
