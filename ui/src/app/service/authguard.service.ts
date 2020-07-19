import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService implements CanActivate {

  constructor(private loginService: LoginService) { }

  canActivate(route : ActivatedRouteSnapshot, state: RouterStateSnapshot) : boolean {
    var isLoggedIn = localStorage.getItem('APP_TOKEN') !== null || this.loginService.getLoggedIn();
    return isLoggedIn;
  }
}
