package com.tushar.eventstores.example.kanban.task.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class KanbanTaskQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbanTaskQueryApplication.class, args);
	}

}
