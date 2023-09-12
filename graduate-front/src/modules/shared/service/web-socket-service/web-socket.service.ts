import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { environment } from 'src/environments/environment';
import { Store } from '@ngxs/store';
import { AddNotification, UpdateNotificationsNumber } from '../../actions/app.action';
import { Notification } from '../../model/notification';
@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private stompClient = null;
  initialized = false;

  constructor(
   
    private store: Store,
   
  ) {
    if (!this.stompClient) {
      this.initialized = false;
      this.connect();
    }
  }

  connect() {
    if (!this.initialized && sessionStorage.getItem('email') !== null) {
      this.initialized = true;
      const serverUrl = environment.webSocketUrl;
      const ws = new SockJS(serverUrl);
      this.stompClient = Stomp.over(ws);

      const that = this;

      this.stompClient.connect({}, function () {
       that.newOrderNotification();
       that.rejectOrderNotification();
      });
    }
  }

  disconnect(): void {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }

    this.initialized = false;
  }

  newOrderNotification() {
    this.stompClient.subscribe(
      environment.publisherUrl +
      sessionStorage.getItem('email') +
      '/new-order',
      message => {
        this.store.dispatch(new UpdateNotificationsNumber());
        const notification: Notification = JSON.parse(
          message.body
        );
        this.store.dispatch(new AddNotification(notification));
      }
    );
  }

  rejectOrderNotification() {
    console.log('ana');
    this.stompClient.subscribe(
      environment.publisherUrl +
      sessionStorage.getItem('email') +
      '/rejected-order',
      message => {
        console.log(message.body);
        this.store.dispatch(new UpdateNotificationsNumber());
        const notification: Notification = JSON.parse(
          message.body
        );
        this.store.dispatch(new AddNotification(notification));
      }
    );
  }
}

  