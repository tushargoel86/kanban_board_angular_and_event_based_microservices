package com.tushar.eventstores.example.kanban.resources;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.eventstores.example.kanban.model.BoardQueryEntity;
import com.tushar.eventstores.example.kanban.service.BoardQueryService;

@RestController
@RequestMapping("/boards")
public class BoardQueryEndpoint {

	private final BoardQueryService boardQueryService;

	public BoardQueryEndpoint(BoardQueryService boardQueryService) {
		this.boardQueryService = boardQueryService;
	}
	

	@GetMapping("/{title}")
	@CrossOrigin(origins = "https://localhost:4200")
	public List<BoardQueryEntity> getBoard(@PathVariable String title) {
		if (title == null) throw new IllegalArgumentException("board title is required");
		return boardQueryService.getBoard(title);
	}
	
	@GetMapping("/{title}/events")
	@CrossOrigin(origins = "https://localhost:4200")
	public List<Object> listAllEventsForBoard(@PathVariable String title) {
		if (title == null) throw new IllegalArgumentException("board title is required");
		return boardQueryService.listBoardEvents(title);
	}
	
	@GetMapping("/")
	@CrossOrigin(origins = "https://localhost:4200")
	public List<String> boards() {
		return boardQueryService.fetchBoards();
	}
}
