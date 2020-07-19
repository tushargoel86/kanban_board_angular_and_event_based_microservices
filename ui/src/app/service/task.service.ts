import { Injectable } from '@angular/core';
import Task from '../components/model/Task';
import { TaskType } from '../components/model/TaskType';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  tasks: Task[] = [];

  constructor() { 
    this.tasks = [ {
      id: 1,
      description: 'Hello world',
      status: TaskType.TODO
    },
    {
      id: 2,
      description: 'Hello world 2',
      status: TaskType.TODO
    },
  ];
  }

  getTaskById(id: number) : Observable<Task> {
    return of(this.tasks[id]);
  }

  updateTask(task: Task): Observable<Task> {
    var filteredTask : Task = this.tasks.find(tsk => tsk.id === task.id);
    filteredTask.status = task.status;
    filteredTask.description = task.description;  
    
    return of (filteredTask);
  }
}
