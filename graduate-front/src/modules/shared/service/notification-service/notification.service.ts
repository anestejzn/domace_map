import { HttpClient } from "@angular/common/http";
import { ConfigService } from "../config-service/config.service";
import { Injectable } from "@angular/core";
import { Notification } from "../../model/notification";

@Injectable({
    providedIn: 'root'
  })
  export class NotificationService {
  
    constructor(private http: HttpClient, private configService: ConfigService) { }

    getAllForUser(userId: number) {
        return this.http.get<Notification[]>(this.configService.getNotificationsForUserUrl(userId));
    }

    readAll(userId: number) {
        return this.http.get<Notification[]>(this.configService.getReadNotificationsForUserUrl(userId));
    }
  }