import { Component, OnInit, OnDestroy } from '@angular/core';
import { KanbanService } from 'src/app/service/kanban.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styles: [],
})
export class BoardComponent implements OnInit {
  constructor(private kanbanService: KanbanService, private router: Router) {}

  ngOnInit(): void {}

  submitBoard(event, userName: string, title: string) {
    event.preventDefault();
    this.kanbanService.createBoard(userName, title).subscribe(
      (inp) => {
        if (inp !== null) alert('board is creared succesfullly');
        let url = '/board/' + title;
        this.router.navigate([url]);
      },
      (error) => {
        alert(JSON.stringify(error.error));
      }
    );
  }
}
