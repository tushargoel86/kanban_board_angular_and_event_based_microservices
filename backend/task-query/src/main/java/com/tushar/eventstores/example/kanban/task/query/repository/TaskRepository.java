package com.tushar.eventstores.example.kanban.task.query.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tushar.eventstores.example.kanban.task.query.model.TaskQuery;

@Repository
public interface TaskRepository extends JpaRepository<TaskQuery, String> {

	@Query("from TaskQuery where boardTitle = :boardTitle")
	public List<TaskQuery> findAllTaskFor(String boardTitle);
	

//	@Query("update taskQuery set description = :task")
//	public TaskQuery updateTask(TaskQuery task);
}
