import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Order, OrderRequest } from "../../model/order";
import { ConfigService } from "../config-service/config.service";
import { RejectOrderRequest } from "../../model/reject-order-request";

@Injectable({
    providedIn: 'root'
  })
  export class OrderService {

    constructor(private http: HttpClient, private configService: ConfigService){}

    saveOrder(order: OrderRequest){
        return this.http.post(this.configService.ORDER_URL, order);
    }

    getOrders(forHousehold: boolean, id: number) {
      return this.http.get<Order[]>(this.configService.getOrdersUrl(forHousehold, id));
    }

    getOrder(id: number) {
      return this.http.get<Order>(this.configService.getOrderUrl(id));
    }

    markAsSent(id: number) {
      return this.http.get<Order>(this.configService.getMarkAsSentOrderUrl(id));
    }

    markAsDelivered(id: number) {
      return this.http.get<Order>(this.configService.getMarkAsDeliveredOrderUrl(id));
    }

    rejectOrder(rejectOrder: RejectOrderRequest){
      return this.http.post(this.configService.REJECT_ORDER_URL, rejectOrder);
    }
  };