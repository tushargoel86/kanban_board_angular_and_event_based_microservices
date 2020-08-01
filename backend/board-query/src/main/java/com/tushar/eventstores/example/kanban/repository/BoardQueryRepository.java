package com.tushar.eventstores.example.kanban.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tushar.eventstores.example.kanban.model.BoardQueryEntity;

@Repository
public interface BoardQueryRepository extends JpaRepository<BoardQueryEntity, String> {

	@Query("select boardTitle from BoardQueryEntity")
	List<String> findAllBoards();
	

	@Query("from BoardQueryEntity where boardTitle = :title")
	List<BoardQueryEntity> findAllById(String title);

}
