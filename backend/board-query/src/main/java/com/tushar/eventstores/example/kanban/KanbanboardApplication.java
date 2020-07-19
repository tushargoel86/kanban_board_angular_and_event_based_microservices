package com.tushar.eventstores.example.kanban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tushar.eventstores.example.kanban.board.aggregrate.Board;

@EnableJpaRepositories
@SpringBootApplication
public class KanbanboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbanboardApplication.class, args);
	}

}
