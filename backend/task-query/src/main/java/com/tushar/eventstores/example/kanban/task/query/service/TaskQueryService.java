package com.tushar.eventstores.example.kanban.task.query.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tushar.eventstores.example.kanban.task.query.model.TaskQuery;
import com.tushar.eventstores.example.kanban.task.query.repository.TaskRepository;

@Service
public class TaskQueryService {

	private TaskRepository taskRepository;

	
	public TaskQueryService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public Optional<TaskQuery> getTaskBy(String id) {
		return taskRepository.findById(id);
	}

	public List<TaskQuery> findAllTaskFor(String boardTitle) {
		List<TaskQuery> taskQuery = taskRepository.findAllTaskFor(boardTitle);
		if (taskQuery.size() != 0) {
			return taskQuery;
		} else {
			 throw new NoSuchElementException(boardTitle + " is not present");
		}
		
	}
	
//	public void deleteTask(String taskId) {
//		Optional<TaskQuery> taskQuery = taskRepository.findById(taskId);
//		if (taskQuery.isPresent()) {
//			taskRepository.deleteById(taskId);
//		} else {
//			 throw new NoSuchElementException(taskId + " is not present");
//		}
//	}
//	
//	public TaskQuery updateTask(TaskQuery task) {
//		Optional<TaskQuery> taskQuery = taskRepository.findById(task.getTaskId());
//		if (taskQuery.isPresent()) {
//			TaskQuery taskOrig = taskQuery.get();
//			taskOrig.setBoardTitle(task.getBoardTitle());
//			taskOrig.setDescription(task.getDescription());
//			taskOrig.setTaskType(task.getTaskType());
//			return taskRepository.save(task);	
//		} else {
//			 throw new NoSuchElementException(task.getTaskId() + " is not present");
//		}
//		
//	}
//	
	 
}
