import { Injectable } from '@angular/core';
import {CanActivate, Router } from '@angular/router';
import { AuthService } from 'src/modules/auth/service/auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class UnauthGuard implements CanActivate {
  constructor(public authService: AuthService, public router: Router) {}

  canActivate(): boolean {
    if (this.authService.getLoggedParsedUser()){
      this.router.navigate(["/map/home"]);

      return false;
    }

    return true;
  }

}
