package com.tushar.eventstores.example.kanban.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardQueryEntity {

	@Id
	private String id;
	private String title;
    private String createdBy;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private String updatedBy;;
}
