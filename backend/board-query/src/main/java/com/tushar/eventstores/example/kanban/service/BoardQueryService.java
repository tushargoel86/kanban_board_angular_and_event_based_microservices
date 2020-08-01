package com.tushar.eventstores.example.kanban.service;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import com.tushar.eventstores.example.kanban.board.aggregrate.Board;
import com.tushar.eventstores.example.kanban.model.BoardQueryEntity;
import com.tushar.eventstores.example.kanban.repository.BoardQueryRepository;

@Service
public class BoardQueryService {

	private final EventStore eventStore;
	private final BoardQueryRepository boardQueryRepository;

	public BoardQueryService(EventStore eventStore, BoardQueryRepository boardQueryRepository) {
		this.eventStore = eventStore;
		this.boardQueryRepository = boardQueryRepository;
	}
	
    public List<Object> listBoardEvents(String title) {
    	return eventStore.readEvents(title)
    					 .asStream()
    					 .map(s -> s.getPayload())
    					 .collect(Collectors.toList());
    }

    public List<BoardQueryEntity> getBoard(String title) {
    	return boardQueryRepository.findAllById(title);
    }

	public List<String> fetchBoards() {
		return boardQueryRepository.findAllBoards();
	}
    
    
}
