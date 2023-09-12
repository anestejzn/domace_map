import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConfigService } from '../config-service/config.service';
import { Review } from '../../model/review';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient, private configService: ConfigService) { }

  save(review: Review) {
    return this.http.post(this.configService.REVIEW_URL, review);
  }

  getReviewsForOffer(offerId: number) {
    return this.http.get<Review[]>(this.configService.getReviewsForOfferIdUrl(offerId));
  }

  deleteReview(id: number, offerId: number) {
    return this.http.delete(this.configService.deleteReviewUrl(id, offerId));
  }

  getReviewsWithPagination(pageNumber: number, pageSize: number): Observable<Review[]>{
    return this.http.get<Review[]>(this.configService.getReviewsUrl(pageNumber, pageSize));
  }
}
