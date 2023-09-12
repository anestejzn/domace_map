import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RejectOrderRequest } from '../../model/reject-order-request';
import { OrderService } from '../../service/order-service/order.service';

@Component({
  selector: 'app-reject-order-dialog',
  templateUrl: './reject-order-dialog.component.html',
  styleUrls: ['./reject-order-dialog.component.scss']
})
export class RejectOrderDialogComponent implements OnInit {

  customReason = '';
  defaultReason = '';
  confirmIsPress = false;
  orderId: number;

  constructor(
    public dialogRef: MatDialogRef<RejectOrderDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data,
    private orderService: OrderService
  ) {
    this.orderId = data.orderId;
  }
  ngOnInit(): void {}

  cancel(): void {
    this.dialogRef.close();
  }

  confirm(): void {
    this.confirmIsPress = true;
    if (this.isValidForm() && this.confirmIsPress){
      const rejectOrderRequest: RejectOrderRequest = {
        id: this.orderId,
        reason: this.defaultReason !=='' ? this.defaultReason : this.customReason
      };
      this.orderService.rejectOrder(rejectOrderRequest).subscribe(
        response => {
          this.dialogRef.close();
        }
      )
      
    }
  }

  isValidForm() {
    return this.defaultReason !== '' || this.customReason !== '';
  }

}
