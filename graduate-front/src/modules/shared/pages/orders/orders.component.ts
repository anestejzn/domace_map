import { Component, OnInit } from '@angular/core';
import { Order } from '../../model/order';
import { OrderService } from '../../service/order-service/order.service';
import { Observable, Subscription } from 'rxjs';
import { AppState } from '../../state/app.state';
import { Select } from '@ngxs/store';
import { Household } from '../../model/household';
import { User } from '../../model/user';
import { OrderDataSource } from '../../model/order-data-source';
import { Router } from '@angular/router';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  orders: Order[] = [];
  dataSource: OrderDataSource;
  orderSubscription: Subscription;
  @Select(AppState.getHousehold)
  household: Observable<Household>;
  storedHousehold: Household;
  @Select(AppState.getUser)
  user: Observable<User>;
  storedUser: User;
  displayedColumns: string[] = ['date', 'household', 'user', 'number', 'status'];

  constructor(private orderService: OrderService, private router: Router) { }

  ngOnInit(): void {
    let forHousehold = true;
    let id = 0;
    this.household.subscribe(responseHousehold => {
      this.user.subscribe(responseUser => {
        this.storedUser = responseUser;
        this.storedHousehold = responseHousehold;
        if(responseHousehold === null) {
          forHousehold = false;
          id = responseUser.id;
        } else {
          id = responseHousehold.id;
        }
        this.orderSubscription = this.orderService.getOrders(forHousehold, id).subscribe(
          response => 
          {
            this.orders = response;
            this.dataSource = new OrderDataSource(response);
          }
        )
      });
    })
  }

  clickedOrder(id: number) {
    this.router.navigate([`/map/order`, id]);
  }

}
