import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { ConfigService } from 'src/modules/shared/service/config-service/config.service';
import { HttpClient } from '@angular/common/http';
import { LoginRequest } from '../../model/login/login-request';
import { LoginResponse } from '../../model/login/login-response';
import { User } from 'src/modules/shared/model/user';
import { ConfirmPinRequest } from '../../model/confirm-pin-request/confirm-pin-request';
import { RegularUser } from 'src/modules/shared/model/regular-user';
import { AddUser } from 'src/modules/shared/actions/app.action';
import { Store } from '@ngxs/store';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  public currentUser$: BehaviorSubject<User>;
  
  constructor(private configService: ConfigService, private http: HttpClient, private store: Store) {
    this.currentUser$ = new BehaviorSubject<User>(null);
  }
  
  login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(
      this.configService.getLoginUrl(),
      loginRequest
      );
    }
    

  getLoggedParsedUser(): User {
    const userString = sessionStorage.getItem('user');
    if (userString !== null && userString !== undefined) {
      return JSON.parse(userString);
    }

    return undefined;
  }


  setSessionStorage(loginResponse: LoginResponse): void {
    sessionStorage.setItem('token', loginResponse.token);
    sessionStorage.setItem('user', JSON.stringify(loginResponse.user));
    sessionStorage.setItem('email', loginResponse.user.email);
    this.currentUser$.next(loginResponse.user);
    this.store.dispatch(new AddUser(loginResponse.user));
  }

  logOut(): Observable<null> {
    // this.webSocketService.disconnect();
    this.currentUser$.next(null);
    return this.http.post<null>(
      this.configService.getLogoutUrl(),
      null
    );
  }


  getSubjectCurrentUser(): BehaviorSubject<User> {
    const user = sessionStorage.getItem('user');
    if (user !== null && user !== undefined) {
      const parsedUser: User = JSON.parse(user);
      this.currentUser$.next(parsedUser);
    } 

    return this.currentUser$;
  }

  confirmPin(confirmPinRequest: ConfirmPinRequest){
    return this.http.put<boolean>(this.configService.CONFIRM_PIN_URL, confirmPinRequest);
  }

}
