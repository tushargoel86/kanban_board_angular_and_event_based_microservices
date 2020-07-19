import { Injectable } from '@angular/core';
import Kanban from '../components/model/Kanban';
import { TaskType } from '../components/model/TaskType';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOption = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' }),
};

@Injectable({
  providedIn: 'root',
})
export class KanbanService {
  url: string = "http://localhost:9090/boards/";
  fetch_all_boards : string = "http://localhost:8080/boards/";
  kanbans: Kanban[] = [];
  boardNames : string[];

  constructor(private http: HttpClient) {
    this.kanbans = [
      {
        id: 1,
        tasks: [
          {
            id: 1,
            description: 'Hello world',
            status: TaskType.TODO,
          },
          {
            id: 2,
            description: 'Hello world 2',
            status: TaskType.TODO,
          },
          // {
          //   id: 3,
          //   description: 'In progress',
          //   status: TaskType.INPROGRESS,
          // },
          {
            id: 2,
            description: 'Completed',
            status: TaskType.DONE,
          },
        ],
        title: 'sample board'
      },
    ];
  }

  getKanbanBoardById(id: number): Observable<Kanban> {
    var kanban : Kanban = this.kanbans.find(knbn => knbn.id === id);
    return of(kanban); 
  }

  createBoard(name: string, title: string) {
    const todayDate = new Date();

    var board = {
      title: title,
      createdBy: name,
      createdAt: '05-06-2020'
     // createdAt: todayDate.getDay() + '-' + todayDate.getMonth() + "-" + todayDate.getFullYear()
    };

    return this.http.post(this.url, JSON.stringify(board), httpOption);
  }

  boards(): Observable<object> {
    return this.http.get(this.fetch_all_boards, httpOption)
  }

  addBoardNames(input: string[]) {
    this.boardNames = input;
  }

  getBoardNames() : Observable<string[]> {
    return of(this.boardNames);
  }
   
}
