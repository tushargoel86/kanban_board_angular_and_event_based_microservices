import { Component, OnInit, OnDestroy} from '@angular/core';
import { LoginService } from 'src/app/service/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
 
  constructor(
    private loginService: LoginService,
    private router : Router
  ) {}

  ngOnInit() {
    this.loginService.subscribeToAuthState();
    this.loginService.setLoggedIn(false);
  }

  socialSignIn(account: string) {
    this.loginService.socialLogin(account);
  }

  onFormSubmit(email: string, password: string) {
    this.loginService.login(email, password).subscribe(loggedIn => {
      if (loggedIn)
         this.router.navigate(['/home']);
      else
          this.router.navigate(['login']);
    });
    
  }
  // ngOnDestroy() {
  //   this.loginService.signOut();
  // }
}
