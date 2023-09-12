import {Component, OnDestroy, OnInit} from '@angular/core';

import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription} from 'rxjs';
// import {
//   FacebookLoginProvider, GoogleLoginProvider,
//   SocialAuthService,
// } from '@abacritt/angularx-social-login';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginResponse } from '../../model/login/login-response';
import { LoginRequest } from '../../model/login/login-request';
import { AuthService } from '../../service/auth/auth.service';
import { AddNotifications, AddUser, UpdateCartNumber, UpdateNotificationsNumber } from 'src/modules/shared/actions/app.action';
import { WebSocketService } from 'src/modules/shared/service/web-socket-service/web-socket.service';
import { NotificationService } from 'src/modules/shared/service/notification-service/notification.service';
import { Store } from '@ngxs/store';
import { Notification } from 'src/modules/shared/model/notification';
// import {WebSocketService} from "../../../shared/services/web-socket-service/web-socket.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit, OnDestroy {
  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [
      Validators.required
    ]),
  });

  hide = true;
  subscriptionLoginWithGmail: Subscription;
  authSubscription: Subscription;
  notificationSubscription: Subscription;

  constructor(
    // private authService: SocialAuthService,
    private authService: AuthService,
    private router: Router,
    private toast: ToastrService,
    private webService: WebSocketService,
    private notificationService: NotificationService,
    private store: Store
  ) {}

  ngOnInit(): void {
    const router = this.router;
    const toast = this.toast;
    // this.authService.authState.subscribe(user => {
    //   if (user && user.provider !== 'FACEBOOK'){
    //     authService.loginWithGoogle(user.idToken).subscribe({
    //       next(loggedUser: LoginResponse): void {
    //         authService.setLocalStorage(loggedUser);
    //         chatService.connect();
    //         router.navigate(['/serb-uber/user/map-page-view/-1']);
    //       },
    //       error(): void {
    //         toast.error('Email or password is not correct!', 'Login failed');
    //       },
    //     });
    //   }
    // })
  }

  // refreshToken(): void {
  //   this.authService.refreshAuthToken(GoogleLoginProvider.PROVIDER_ID);
  // }

  // signInWithFB(): void {
  //   const router = this.router;
  //   const toast = this.toast;
  //   const chatService = this.chatService;
  //   this.authService.signIn(FacebookLoginProvider.PROVIDER_ID).then(data => {
  //     const authService = this.social;
  //     this.social.loginWithFacebook(data.authToken).subscribe({
  //       next(loggedUser: LoginResponse): void {
  //         authService.setLocalStorage(loggedUser);
  //         chatService.connect();
  //         router.navigate(['/serb-uber/user/map-page-view/-1']);
  //       },
  //       error(): void {
  //         toast.error('Email or password is not correct!', 'Login failed');
  //       },
  //     });
  //   });
  // }


  logIn() {
    if (!this.loginForm.invalid) {
      const router = this.router;
      const toast = this.toast;
      const loginRequest: LoginRequest = {
        email: this.loginForm.get('email').value,
        password: this.loginForm.get('password').value,
      };
      this.authSubscription = this.authService.login(loginRequest).subscribe((user:LoginResponse) => {
          this.authService.setSessionStorage(user);
          router.navigate(['/map/home']);
          this.webService.connect();
          this.loadNotifications(user.user.id);
        },
        error => {
          toast.error('Email ili lozinka nisu ispravni!', 'Neuspešna prijava');
        });
    }
  }

  loadNotifications(userId: number){
    this.notificationSubscription = this.notificationService.getAllForUser(userId).subscribe(
      response => {
        this.store.dispatch(new AddNotifications(response))
        this.filterUnreadNotifications(response);
      },
      error => {
        this.toast.error(error.error);
      }
    )
  }

  filterUnreadNotifications(notifications: Notification[]){
    for(const notification of notifications){
      if(!notification.read){
        this.store.dispatch(new UpdateNotificationsNumber());
      }
    }
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
    if (this.subscriptionLoginWithGmail) {
      this.subscriptionLoginWithGmail.unsubscribe();
    }
  }
}

