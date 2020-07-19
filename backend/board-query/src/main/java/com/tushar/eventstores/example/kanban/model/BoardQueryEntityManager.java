package com.tushar.eventstores.example.kanban.model;

import javax.persistence.EntityManager;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.tushar.eventstores.example.kanban.board.event.CreateBoardEvent;
import com.tushar.eventstores.example.kanban.board.event.UpdateBoardEvent;
import com.tushar.eventstores.example.kanban.repository.BoardQueryRepository;

@Component
public class BoardQueryEntityManager {

	private BoardQueryRepository boardRepository;
	
	private EntityManager manager;

	public BoardQueryEntityManager(BoardQueryRepository boardRepository, EntityManager manager) {
		this.boardRepository = boardRepository;
		this.manager = manager;
	}

	@EventHandler
	void on(CreateBoardEvent event) {
		String id = event.getId().toString();
		
		//System.out.println(event.getId() + " " + event.getCreatedBy() + "  " + event.getCreatedAt());
		//Board board = getBoardFromEvent(id);
		BoardQueryEntity queryEntity = boardRepository.findById(id).orElse(new BoardQueryEntity());
		
		queryEntity.setId(id);
		queryEntity.setCreatedDate(event.getCreatedAt());
		queryEntity.setCreatedBy(event.getCreatedBy());
		queryEntity.setTitle(event.getTitle());
		//queryEntity.setUpdatedBy(event.getUpdatedBy());
	//	queryEntity.setUpdatedDate(event.getUpdatedDate());
		
		boardRepository.save(queryEntity);
	}
	
	@EventHandler
	void on(UpdateBoardEvent event) {
		String id = event.getId().toString();
		
		//System.out.println(event.getId() + " " + event.getCreatedBy() + "  " + event.getCreatedAt());
		//Board board = getBoardFromEvent(id);
		BoardQueryEntity queryEntity = boardRepository.findById(id).orElse(new BoardQueryEntity());
		
		queryEntity.setId(id);
		queryEntity.setTitle(event.getTitle());
		queryEntity.setUpdatedBy(event.getUpdatedBy());
		queryEntity.setUpdatedDate(event.getUpdatedAt());
		
		boardRepository.save(queryEntity);
	}
	
	
}
