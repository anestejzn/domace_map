import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Review } from 'src/modules/shared/model/review';
import { ReviewService } from 'src/modules/shared/service/review-service/review.service';

@Component({
  selector: 'app-all-reviews',
  templateUrl: './all-reviews.component.html',
  styleUrls: ['./all-reviews.component.scss']
})
export class AllReviewsComponent implements OnInit {
  reviews: Review[];
  pageSize = 6;
  totalPages:number;
  currentPage = 0;
  reviewsSubscription: Subscription;

  constructor(private reviewService: ReviewService) { }

  ngOnInit(): void {
    this.loadReviews();
  }

  loadReviews() {
    this.reviewsSubscription = this.reviewService
      .getReviewsWithPagination(this.currentPage, this.pageSize)
      .subscribe((response: Review[]) => {
        this.reviews = response;
        if(response.length > 0){
          this.totalPages = this.reviews[0].pageNumber;
        }
      });
  }

  changePage(newPage: number) {
    this.currentPage = newPage;
    this.loadReviews();
  }


}
