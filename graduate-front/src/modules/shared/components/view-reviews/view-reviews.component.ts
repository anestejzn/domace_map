import { Component, Inject, OnInit } from '@angular/core';
import { Review } from '../../model/review';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ReviewService } from '../../service/review-service/review.service';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/modules/auth/service/auth/auth.service';
import { User } from '../../model/user';

@Component({
  selector: 'app-view-reviews',
  templateUrl: './view-reviews.component.html',
  styleUrls: ['./view-reviews.component.scss']
})
export class ViewReviewsComponent implements OnInit {
  reviews: Review[] = [];
  offerId: number;
  authUser: User;
  reviewSubscription: Subscription;
  authSubscription: Subscription;

  constructor(
    private dialogRef: MatDialogRef<ViewReviewsComponent>,
    @Inject(MAT_DIALOG_DATA) data,
    private reviewService: ReviewService,
    private authService: AuthService
  ) {

    this.offerId = data;
  }

  isAdmin() {
    return this.authUser.role.roleName === 'ROLE_ADMIN';
  }

  deleteReview(id: number, offerId: number) {
    this.reviewService.deleteReview(id, offerId).subscribe(response => 
        {
          this.ngOnInit();
        }
      )
  }

  ngOnInit(): void {
    this.authSubscription = this.authService
      .getSubjectCurrentUser()
      .subscribe((user: User) => {
        this.authUser = user;
      });
    this.reviewSubscription = this.reviewService.getReviewsForOffer(this.offerId).subscribe(
      response => {
        console.log(response);
        this.reviews = response;
      }
    )
  }

}
