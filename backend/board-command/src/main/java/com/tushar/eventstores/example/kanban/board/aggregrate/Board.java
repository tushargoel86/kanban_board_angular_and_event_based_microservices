package com.tushar.eventstores.example.kanban.board.aggregrate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

import java.time.LocalDate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tushar.eventstores.example.kanban.board.command.CreateBoardCommand;
import com.tushar.eventstores.example.kanban.board.command.DeleteBoardCommand;
import com.tushar.eventstores.example.kanban.board.command.UpdateBoardCommand;
import com.tushar.eventstores.example.kanban.board.event.CreateBoardEvent;
import com.tushar.eventstores.example.kanban.board.event.DeleteBoardEvent;
import com.tushar.eventstores.example.kanban.board.event.UpdateBoardEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Aggregate
@Data
@NoArgsConstructor
public class Board {

	@AggregateIdentifier
	private String boardTitle;
    private String createdBy;
    
    @JsonFormat(pattern = "dd-mm-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate createdDate;
    
    @JsonFormat(pattern = "dd-mm-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate updatedDate;
    private String updatedBy;

    @CommandHandler
    public Board(CreateBoardCommand cmd) {
    	apply(new CreateBoardEvent(cmd.getTitle(), cmd.getCreatedAt(), cmd.getCreatedBy()));
    }
    
    @EventSourcingHandler
    private void createBoard(CreateBoardEvent event) {
    	this.boardTitle = event.getBoardTitle();
    	this.createdDate = event.getCreatedAt();
    	this.createdBy = event.getCreatedBy();
    }
    
    @CommandHandler
    public void on(UpdateBoardCommand cmd) {
    	apply(new UpdateBoardEvent(cmd.getTitle(), cmd.getUpdatedAt(), cmd.getUpdatedBy()));
    }
    
    @EventSourcingHandler
    private void updateBoard(UpdateBoardEvent event) {
    	this.boardTitle = event.getBoardTitle();
    	this.updatedDate = event.getUpdatedAt();
    	this.updatedBy = event.getUpdatedBy();
    }
    
    @CommandHandler
    public void on(DeleteBoardCommand cmd) {
    	apply(new DeleteBoardEvent(cmd.getTitle()));
    }
    
    @EventSourcingHandler
    public void deleteBoard(DeleteBoardEvent event) {
    	markDeleted();
    }
}
