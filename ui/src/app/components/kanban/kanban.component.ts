import { Component, OnInit, OnDestroy } from '@angular/core';
import Kanban from '../model/Kanban';
import Task from '../model/Task';
import { KanbanService } from 'src/app/service/kanban.service';
import { TaskService } from 'src/app/service/task.service';
import { Route } from '@angular/compiler/src/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskType } from '../model/TaskType';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { stringify } from 'querystring';
import { Observable, of, BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-kanban',
  templateUrl: './kanban.component.html',
  styleUrls: ['./kanban.component.css'],
})
export class KanbanComponent implements OnInit {
  kanban: Kanban;
  todos: Task[] = [];
  inprogress: Task[] = [];
  done: Task[] = [];
  default: Task[] = [
    {
      id: 0,
      description: '',
      status: null,
    },
  ];

  private boardNames = new BehaviorSubject<string[]>([]);
  private boardNamesObserver = this.boardNames.asObservable();


  constructor(
    private kanbanService: KanbanService,
    private taskService: TaskService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.kanbanService.getKanbanBoardById(1).subscribe((res) => {
      this.kanban = res;
      this.splitTaskByType(res);
    });
  }

  splitTaskByType(kanban: Kanban) {
    this.todos = kanban.tasks.filter((task) => task.status === TaskType.TODO);
    this.inprogress = kanban.tasks.filter(
      (task) => task.status === TaskType.INPROGRESS
    );

    if (this.inprogress.length === 0) this.inprogress = this.default;
  }

  drop(event: CdkDragDrop<Task[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }

    if (this.inprogress.length === 0) this.inprogress = this.default;

    if (this.todos.length === 0) this.todos = this.default;

    if (this.done.length === 0) this.done = this.default;
  }

  
  // boards() {
  //   this.kanbanService.boards().subscribe(inp => {
  //       console.log('boards', typeof inp);
  //       if (inp) {
  //         console.log('inp', Object.values(inp));
  //         this.kanbanService.addBoardNames(Object.values(inp));
  //       }
  //     }  
  //   );
  // }

  getBoardNameObserver() {
    return this.boardNamesObserver;
  }

   
}
