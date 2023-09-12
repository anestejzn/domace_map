import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { ReviewService } from '../../service/review-service/review.service';
import { Review } from '../../model/review';

@Component({
  selector: 'app-rate-order-dialog',
  templateUrl: './rate-order-dialog.component.html',
  styleUrls: ['./rate-order-dialog.component.scss']
})
export class RateOrderDialogComponent implements OnInit {
  rate = 0;
  offerId: number;
  userId: number;
  offerOrderId: number;
  comment = '';
  error = '';

  reviewSubscription: Subscription;

  constructor(
    private dialogRef: MatDialogRef<RateOrderDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data,
    public reviewService: ReviewService
  ) {
    this.offerId = data.offerId;
    this.userId = data.userId;
    this.offerOrderId = data.id;
  }
  ngOnInit(): void {}

  onRatingSet(value: number): void {
    this.rate = value;
  }

  confirm(): void {

  if(this.rate === 0){
    this.error = "Ocena mora biti izmedju 1 i 5.";
  }
  else{
    let review: Review = {
      text: this.comment,
      offerId: this.offerId,
      userId: this.userId,
      rate: this.rate,
      offerOrderId: this.offerOrderId
    }
    this.reviewSubscription = this.reviewService.save(review).subscribe(
      response => {
        this.dialogRef.close(true);
      });
    }
  }

}
