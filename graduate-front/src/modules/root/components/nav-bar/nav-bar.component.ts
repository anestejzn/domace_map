import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Select, Store } from '@ngxs/store';
import { Observable, Subscription } from 'rxjs';
import { AuthService } from 'src/modules/auth/service/auth/auth.service';
import { AddHousehold, ClearStore, RemoveNotificationsNumber } from 'src/modules/shared/actions/app.action';
import { User } from 'src/modules/shared/model/user';
import { NotificationService } from 'src/modules/shared/service/notification-service/notification.service';
import { UserService } from 'src/modules/shared/service/user-service/user.service';
import { AppState } from 'src/modules/shared/state/app.state';
import { Notification } from 'src/modules/shared/model/notification';
import { ToastrService } from 'ngx-toastr';
import { WebSocketService } from 'src/modules/shared/service/web-socket-service/web-socket.service';
@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {
  loggedUser:User;
  authSubscription: Subscription;
  notificationSubscription: Subscription;
  haveHousehold = false;
  @Select(AppState.getCartNumber)
  cartNumber: Observable<number>;
  storedCartNumber: number;
  @Select(AppState.getNotificationsNumber)
  notificationsNumber: Observable<number>;
  storedNotificationsNumber: number;
  @Select(AppState.getNotifications)
  notifications: Observable<Notification[]>;
  storedNotifications: Notification[] = [];

  constructor(
    private authService: AuthService, 
    private userService: UserService, 
    private router: Router,
    private store: Store,
    private webSocketService: WebSocketService
  ) { }

  ngOnInit(): void {
    this.authSubscription = this.authService
      .getSubjectCurrentUser()
      .subscribe((user: User) => {
        if(user !== null){
          if(user.role.roleName === 'ROLE_USER'){
            this.userService.getUserHousehold(user.id).subscribe(
              response => {
                if(response !== null){
                  this.haveHousehold = true;
                  this.store.dispatch(new AddHousehold(response));
                }
              }
            )
          }
      }
      console.log(user === null);
        this.loggedUser = user;
      });

      this.cartNumber.subscribe(response => this.storedCartNumber = response);
      this.notificationsNumber.subscribe(response => this.storedNotificationsNumber = response);
      this.notifications.subscribe(response => this.storedNotifications = response);
  }

  seenNotifications(){
    this.store.dispatch(new RemoveNotificationsNumber());

  }

  notificationRedirect(orderId: number) {
    this.router.navigate(['/map/order', orderId]);
  }

  logOut(){
    this.authService.logOut().subscribe(
      response => {
        sessionStorage.clear();
        this.store.dispatch(new ClearStore());
        this.router.navigate(['/map/auth/login']);
        this.webSocketService.disconnect();
      });
  }
  navigateToCart(){
    this.router.navigate(['/map/cart']);
  }

}
