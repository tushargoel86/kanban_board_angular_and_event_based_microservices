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
  BOARD_URL : string = "http://localhost:8080/boards/"; 
  kanbans: Kanban[] = [];
  boardNames : string[];

  constructor(private http: HttpClient) {
    this.kanbans = [
      
    ];
  }

 
  createBoard(name: string, title: string) {
    const todayDate = new Date();

    var board = {
      title: title,
      createdBy: name,
      createdAt: todayDate.toJSON().slice(0, 10).split('-').reverse().join("-")
    };
    return this.http.post(this.url, JSON.stringify(board), httpOption);
  }

  boards(): Observable<object> {
    return this.http.get(this.BOARD_URL, httpOption)
  }

  addBoardNames(input: string[]) {
    this.boardNames = input;
  }

  getBoardNames() : Observable<string[]> {
    return of(this.boardNames);
  }

  deleteBoard(boardTitle: string) : Observable<object> {
     return this.http.delete<object>(this.url+"/"+boardTitle);
  }
}
