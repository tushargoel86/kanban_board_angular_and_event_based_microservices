package com.tushar.eventstores.example.kanban.task.aggregrate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.tushar.eventstores.example.kanban.task.command.CompletedTaskCommand;
import com.tushar.eventstores.example.kanban.task.command.CreateTaskCommand;
import com.tushar.eventstores.example.kanban.task.command.DeleteTaskCommand;
import com.tushar.eventstores.example.kanban.task.command.InprogrssTaskCommand;
import com.tushar.eventstores.example.kanban.task.command.ToDoTaskCommand;
import com.tushar.eventstores.example.kanban.task.command.UpdateTaskCommand;
import com.tushar.eventstores.example.kanban.task.event.CompletedTaskEvent;
import com.tushar.eventstores.example.kanban.task.event.CreateTaskEvent;
import com.tushar.eventstores.example.kanban.task.event.DeleteTaskEvent;
import com.tushar.eventstores.example.kanban.task.event.InprogrssTaskEvent;
import com.tushar.eventstores.example.kanban.task.event.ToDoTaskEvent;
import com.tushar.eventstores.example.kanban.task.event.UpdateTaskEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Aggregate
@Data
@NoArgsConstructor
public class Task {

	@AggregateIdentifier
	private String taskId;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private TaskType taskType;
	
	private String boardTitle;
	
	@CommandHandler
    public Task(CreateTaskCommand cmd) {
    	apply(new CreateTaskEvent(cmd.getId(), cmd.getDescription(), cmd.getTaskType(), cmd.getBoardTitle()));
    }
    
    @EventSourcingHandler
    private void createTask(CreateTaskEvent event) {
    	this.taskId = event.getId();
    	this.description = event.getDescription();
    	this.taskType = event.getTaskType();
    	this.boardTitle = event.getBoardTitle();
    }
   
    @CommandHandler
    public void on(UpdateTaskCommand cmd) {
    	apply(new UpdateTaskEvent(cmd.getId(), cmd.getDescription(), cmd.getTaskType(), cmd.getBoardTitle()));
    }
    
    @EventSourcingHandler
    private void updateTask(UpdateTaskEvent event) {
    	this.taskId = event.getId();
    	this.description = event.getDescription();
    	this.taskType = event.getTaskType();
    	this.boardTitle = event.getBoardTitle();
    }
    
    @CommandHandler
    public void on(DeleteTaskCommand cmd) {
    	apply(new DeleteTaskEvent(cmd.getId()));
    }
    
    @EventSourcingHandler
    private void deleteTask(DeleteTaskEvent event) {
    	 AggregateLifecycle.markDeleted();
    }
    

    @CommandHandler
    public void on(ToDoTaskCommand cmd) {
    	apply(new ToDoTaskEvent(cmd.getId(), cmd.getDescription(), cmd.getTaskType(), cmd.getBoardTitle()));
    }
    
    @EventSourcingHandler
    private void todoTask(ToDoTaskEvent event) {
    	this.taskId = event.getId();
    	this.description = event.getDescription();
    	this.taskType = event.getTaskType();
    	this.boardTitle = event.getBoardTitle();
    }
    
    @CommandHandler
    public void on(InprogrssTaskCommand cmd) {
    	apply(new InprogrssTaskEvent(cmd.getId(), cmd.getDescription(), cmd.getTaskType(), cmd.getBoardTitle()));
    }
    
    @EventSourcingHandler
    private void inprogressTask(InprogrssTaskEvent event) {
    	this.taskId = event.getId();
    	this.description = event.getDescription();
    	this.taskType = event.getTaskType();
    	this.boardTitle = event.getBoardTitle();
    }

    @CommandHandler
    public void on(CompletedTaskCommand cmd) {
    	apply(new CompletedTaskEvent(cmd.getId(), cmd.getDescription(), cmd.getTaskType(), cmd.getBoardTitle()));
    }
    
    @EventSourcingHandler
    private void completedTask(CompletedTaskEvent event) {
    	this.taskId = event.getId();
    	this.description = event.getDescription();
    	this.taskType = event.getTaskType();
    	this.boardTitle = event.getBoardTitle();
    }

	
}
