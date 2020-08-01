import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';
import { KanbanService } from 'src/app/service/kanban.service';

import { faTrash } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  trashIcon = faTrash;
  isAuthenticated : boolean = false;
  boardNames: string[];
 
  constructor(private loginService: LoginService, private kanbanService: KanbanService) { }

  ngOnInit(): void {
    this.loginService.getLoggedInObserver().subscribe((state) => {
      this.isAuthenticated = state || localStorage.getItem("APP_TOKEN") !== null;
    });

    this.kanbanService.boards().subscribe(inp =>  {
      if(inp)
        this.boardNames = Object.values(inp);
    });
  }

  signout() {
    this.loginService.signOut();
  }

  hasAuthenticated(): boolean {
    return this.isAuthenticated;
  }

  deleteBoard(board: string) {
    console.log('request to delete board', board);    
  }
}
