import { Component, OnInit, OnDestroy } from '@angular/core';
import { KanbanService } from 'src/app/service/kanban.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styles: [
  ]
})
export class BoardComponent implements OnInit {

  constructor(private kanbanService: KanbanService) { }

  ngOnInit(): void {
    console.log('board is loaded');
  }

  submitBoard(event, userName: string, title: string) {
    event.preventDefault();    
    this.kanbanService.createBoard(userName, title).subscribe(inp => {
      if (inp !== null)
        alert('board is creared succesfullly');
    },
    error => { 
      alert(error.error.message)
    }
    );

   
  }

}
