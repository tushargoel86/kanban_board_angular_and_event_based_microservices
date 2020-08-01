import { Injectable } from '@angular/core';
import Task from '../components/model/Task';
import { TaskType } from '../components/model/TaskType';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOption = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' }),
};


@Injectable({
  providedIn: 'root'
})
export class TaskService {
  
  TASK_URL:string = "http://localhost:9010/tasks";
  TASK_QUERY_URL:string = "http://localhost:8082/tasks/";
  tasks: Task[] = [];


  constructor(private http: HttpClient) { 
    this.tasks = [  ];
  }

  getAllTasksForBoard<Task>(title: String): Observable<Task[]> {
    return this.http.get<Task[]>(this.TASK_QUERY_URL + title, httpOption);
  }

  getTaskById(id: string) : Observable<Task> {
    return this.http.get<Task>(this.TASK_QUERY_URL+id, httpOption);
  }

  createTask(task: { boardTitle: string; description: string; taskType: TaskType; }) : Observable<object> {
    return this.http.post(this.TASK_URL, JSON.stringify(task), httpOption);
  }

  updateTask(task: Task): Observable<Task> {
     return this.http.put<Task>(this.TASK_URL+"/"+task.taskId, JSON.stringify(task), httpOption);
  }

  deleteTask(taskId: string): Observable<object> {
    return this.http.delete<object>(this.TASK_URL+ "/" + taskId, httpOption);
  }
}
