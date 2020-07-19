import { Injectable, Output, EventEmitter, OnDestroy } from '@angular/core';
import SocialUser from '../components/model/SocialUser';
import {
  GoogleLoginProvider,
  FacebookLoginProvider,
  SocialAuthService,
} from 'angularx-social-login';

import { Router } from '@angular/router';
import { BehaviorSubject, Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService  {
  private user: SocialUser;
  private isLoggedIn: boolean;

  private userSource = new BehaviorSubject<boolean>(false);
  private hasLoggedInObserver = this.userSource.asObservable();
  private email: string = "test@gmail.com";
  private password: string = "abcd1234"

  constructor(
    private authService: SocialAuthService,
    private router: Router) { }

  subscribeToAuthState() {
    this.authService.authState.subscribe((user) => {
      this.user = user;
      this.isLoggedIn = user !== null;
    });
  }

  login(email: string, password: string) : Observable<Boolean> {
     this.isLoggedIn = this.email === email && this.password === password;
     this.userSource.next(this.isLoggedIn);
     return of(this.isLoggedIn);
  }

  socialLogin(account: string) {
    if (account === 'google') this.signInWithGoogle();
    else if (account === 'facebook') this.signInWithFB();
  }

  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }

  signInWithFB(): void {
    var promise = this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);
    promise.then((userData) => {
      this.router.navigate(['/home']);   
      localStorage.setItem("APP_TOKEN", userData.authToken);
      this.userSource.next(true);
    }).catch(alert);
  }
 
  signOut(): void {
    console.log(localStorage.getItem('APP_TOKEN'));
    
    if(localStorage.getItem('APP_TOKEN')) {
      localStorage.removeItem('APP_TOKEN');
      this.authService.signOut();
    }
    
    this.userSource.next(false);
    this.router.navigate(['']);
  }

  getUser() : SocialUser {
    return this.user;
  }

  getLoggedIn(): boolean {
    return this.isLoggedIn;
  }

  getLoggedInObserver(): Observable<boolean> {
    return this.hasLoggedInObserver;
  }

  setLoggedIn(state: boolean) {
    this.userSource.next(state);    
  }

  

  
}
