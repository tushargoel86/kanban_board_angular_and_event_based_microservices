import { Component, OnInit, OnDestroy } from '@angular/core';
import Kanban from '../model/Kanban';
import Task from '../model/Task';
import { KanbanService } from 'src/app/service/kanban.service';
import { TaskService } from 'src/app/service/task.service';
import { ActivatedRoute } from '@angular/router';
import { TaskType } from '../model/TaskType';
import { faTrash, faEdit } from '@fortawesome/free-solid-svg-icons';

import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-kanban',
  templateUrl: './kanban.component.html',
  styleUrls: ['./kanban.component.css'],
})
export class KanbanComponent implements OnInit {
  trashIcon = faTrash;
  editIcon = faEdit;

  kanban: Kanban = {
    title: '',
  };

  backlog: Task[] = [];
  todos: Task[] = [];
  inprogress: Task[] = [];
  done: Task[] = [];
  taskModal: boolean = false;
  default: Task = {
    taskId: '',
    boardTitle: '',
    description: '',
    taskType: TaskType.BACKLOG,
  };

  private boardNames = new BehaviorSubject<string[]>([]);
  private boardNamesObserver = this.boardNames.asObservable();

  constructor(
    private kanbanService: KanbanService,
    private taskService: TaskService,
    private actRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    var title = this.actRoute.snapshot.params.title;
    this.kanban.title = title;
    
    if (title !== undefined) {
      this.taskService.getAllTasksForBoard(title).subscribe(
        (res: Task[]) => {
          if (res !== null) {
            this.kanban = {
              title: res[0].boardTitle,
              tasks: res,
            };
            this.splitTaskByType(this.kanban);
          }
        },
        (error) => {
          console.log(error.error);
        }
      );
    }
  }

  splitTaskByType(kanban: Kanban) {
    this.todos = kanban.tasks.filter((task) => task.taskType === TaskType.TODO);
    this.backlog = kanban.tasks.filter(
      (task) => task.taskType === TaskType.BACKLOG
    );
    this.done = kanban.tasks.filter((task) => task.taskType === TaskType.DONE);
    this.inprogress = kanban.tasks.filter(
      (task) => task.taskType === TaskType.INPROGRESS
    );
  }

  drop(event: CdkDragDrop<Task[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      let type;
      switch (event.container.id) {
        case 'backlog':
          type = TaskType.BACKLOG;
          break;
        case 'todo':
          type = TaskType.TODO;
          break;
        case 'inprogress':
          type = TaskType.INPROGRESS;
          break;
        case 'done':
          type = TaskType.DONE;
          break;
      }

      event.previousContainer.data[event.previousIndex].taskType = type;

      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
      var modified = event.container.data[event.currentIndex];
      this.taskService.updateTask(modified).subscribe(console.log);
    }
  }

  getBoardNameObserver() {
    return this.boardNamesObserver;
  }

  onSubmit(description: string) {
    this.taskService
      .createTask({
        boardTitle: this.kanban.title,
        description: description,
        taskType: TaskType.BACKLOG,
      })
      .subscribe((res) => {
        var op = JSON.parse(JSON.stringify(res));
        if (op.message !== null) {
          this.backlog.unshift({
            taskId: op.message,
            description: description,
            boardTitle: this.kanban.title,
            taskType: TaskType.BACKLOG,
          });
        }
      });
    this.default.description = '';
  }

  removeTask(data: Task[], id: string): Task[] {
    var copy = [...data];
    let index = copy.findIndex((tk) => tk.taskId === id);
    copy.splice(index, 1);
    return copy;
  }

  deleteTask(task: Task) {
    this.taskService.deleteTask(task.taskId).subscribe((res) => {
      if (res) {
        switch (task.taskType) {
          case TaskType.BACKLOG:
            this.backlog = this.removeTask(this.backlog, task.taskId);
            break;
          case TaskType.TODO:
            this.todos = this.removeTask(this.todos, task.taskId);
            break;
          case TaskType.INPROGRESS:
            this.inprogress = this.removeTask(this.inprogress, task.taskId);
            break;
          case TaskType.DONE:
            this.done = this.removeTask(this.done, task.taskId);
            break;
        }
      }
    });
  }

  onDelete() {
    this.kanbanService.deleteBoard(this.kanban.title)
                      .subscribe(alert);
  }
}
