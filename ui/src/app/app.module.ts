import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { KanbanComponent } from './components/kanban/kanban.component';
import { TaskService } from './service/task.service';
import { KanbanService } from './service/kanban.service';

import { DragDropModule } from '@angular/cdk/drag-drop';
import { NavbarComponent } from './components/navbar/navbar.component';
import { BoardComponent } from './components/board/board.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

 import {
  GoogleLoginProvider,
  FacebookLoginProvider,
} from 'angularx-social-login';
import {
  SocialLoginModule,
  SocialAuthServiceConfig,
} from 'angularx-social-login';
import { LoginService } from './service/login.service';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthguardService } from './service/authguard.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    KanbanComponent,
    NavbarComponent,
    BoardComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DragDropModule,
    SocialLoginModule,
    HttpClientModule,
    FormsModule,
    FontAwesomeModule
  ],
  providers: [
    TaskService,
    KanbanService,
    LoginService,
    AuthguardService,
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          // {
          //   id: GoogleLoginProvider.PROVIDER_ID,
          //   provider: new GoogleLoginProvider(
          //     '121368539418-afmb1n71gu0g1bf7b268bqlagdrf7qk4.apps.googleusercontent.com'
          //   ),
          // },
          {
            id: FacebookLoginProvider.PROVIDER_ID,
            provider: new FacebookLoginProvider('219078759218347'),
          },
        ],
      } as SocialAuthServiceConfig,
    },
    {
      provide: 'canActivateTeam',
      useValue: (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) =>
        true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
