import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Review } from 'src/modules/shared/model/review';
import { ReviewService } from 'src/modules/shared/service/review-service/review.service';

@Component({
  selector: 'app-review-row',
  templateUrl: './review-row.component.html',
  styleUrls: ['./review-row.component.scss']
})
export class ReviewRowComponent implements OnInit {
  @Input() review: Review;
  @Output() removeReviewEmitter = new EventEmitter();
  constructor(private reviewService: ReviewService) { }

  ngOnInit(): void {
    console.log(this.review);
  }

  removeReview() {
    this.reviewService.deleteReview(this.review.id, this.review.offer.id).subscribe(
      response => this.removeReviewEmitter.emit()
    )
  }
}
