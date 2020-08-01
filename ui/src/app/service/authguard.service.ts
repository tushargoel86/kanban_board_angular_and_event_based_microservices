import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService implements CanActivate {

  constructor(private loginService: LoginService, private router: Router) { }

  canActivate(route : ActivatedRouteSnapshot, state: RouterStateSnapshot) : boolean {
    var isLoggedIn = localStorage.getItem('APP_TOKEN') !== null || this.loginService.getLoggedIn();
    if (!isLoggedIn) {
        this.router.navigate(['/login']);       
    }
    console.log('isLoggedIn', isLoggedIn);
    
    return isLoggedIn;
  }
}
