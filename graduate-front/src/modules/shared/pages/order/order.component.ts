import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from '../../service/order-service/order.service';
import { Order } from '../../model/order';
import { Household } from '../../model/household';
import { Select } from '@ngxs/store';
import { AppState } from '../../state/app.state';
import { Observable, Subscription, SubscriptionLike } from 'rxjs';
import { OfferDataSource } from '../../model/offer-data-source';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { User } from '../../model/user';
import { RateOrderDialogComponent } from '../../components/rate-order-dialog/rate-order-dialog.component';
import { ToastrService } from 'ngx-toastr';
import { RejectOrderDialogComponent } from '../../components/reject-order-dialog/reject-order-dialog.component';
import { getImageForType } from '../../util/typeOfProduct';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {
  order: Order;
  id: number;
  @Select(AppState.getHousehold)
  household: Observable<Household>;
  storedHousehold: Household = null;
  @Select(AppState.getUser)
  user: Observable<User>;
  storedUser: User = null;
  orderSubscription: Subscription;
  markAsSentSubscription: Subscription;
  markAsDeliveredSubscription: Subscription;
  dataSource: OfferDataSource;
  displayedColumns: string[] = ['name', 'colForPrice', 'col', 'price', 'rate'];

  constructor(
    private route: ActivatedRoute, 
    private orderService: OrderService,
    private dialog: MatDialog,
    private toast: ToastrService
    ) { }

  ngOnInit(): void {
    this.household.subscribe(
      response => this.storedHousehold = response
    );
    this.user.subscribe(
      response => this.storedUser = response
    );
    this.route.params
      .subscribe(params => {
        this.id = params['id'];
        this.orderSubscription = this.orderService.getOrder(this.id).subscribe(
          response => {
            this.order = response;
            console.log(response);
            this.dataSource = new OfferDataSource(response.offerOrders);
          }
        )
    });
  }

  getImage(type: string) {
    return getImageForType(type);
  }

  getDisplayedColumns() {
    
    return this.storedHousehold === null || (this.storedHousehold !== null && this.storedHousehold.id !== this.order.household.id) ?
          ['name', 'colForPrice', 'col', 'price', 'rate'] :
          ['name', 'colForPrice', 'col', 'price'];
  }

  markAsDelivered() {
    this.markAsDeliveredSubscription = this.orderService.markAsDelivered(this.id).subscribe(
      response => this.order = response
    )
  }

  markAsSent() {
    this.markAsSentSubscription = this.orderService.markAsSent(this.id).subscribe(
      response => this.order = response
    )
  }

  reject() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      orderId: this.id,
    };
    const dialogRef = this.dialog.open(RejectOrderDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      response => {
        this.ngOnInit();
        this.toast.success('Kupac je obavešten o odbijanju porudžbine.');
        
      },
      error => {
        this.toast.error('Neuspešno.');
      }
    );
  }

  rate(id: number, offerId: number) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      id: id,
      offerId: offerId,
      userId: this.storedUser.id,
    };
    const dialogRef = this.dialog.open(RateOrderDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      response => {
        this.ngOnInit();
        this.toast.success('Vaša ocena je dodata.');
      },
      error => {
        this.toast.error('Neuspešno dodavanje ocene.');
      }
    );
  }

  getCancelReason(reason: string) {
    return `Razlog: ${reason}`;
  }

  ngOnDestroy() {
    if(this.markAsDeliveredSubscription){
      this.markAsDeliveredSubscription.unsubscribe();
    }
    if(this.markAsSentSubscription){
      this.markAsSentSubscription.unsubscribe();
    }
    if(this.orderSubscription){
      this.orderSubscription.unsubscribe();
    }
  }

}
