import { Component, EventEmitter, Inject, Input, OnInit, Output } from '@angular/core';
import { Offer } from 'src/modules/shared/model/offer';
import { ConfigService } from 'src/modules/shared/service/config-service/config.service';
import { CreateProductDialogComponent } from '../create-product-dialog/create-product-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { OfferService } from 'src/modules/user/service/offer.service';
import { ToastrService } from 'ngx-toastr';
import { ViewReviewsComponent } from 'src/modules/shared/components/view-reviews/view-reviews.component';
import { AppState } from 'src/modules/shared/state/app.state';
import { Observable } from 'rxjs';
import { User } from 'src/modules/shared/model/user';
import { Select } from '@ngxs/store';

@Component({
  selector: 'app-product-row',
  templateUrl: './product-row.component.html',
  styleUrls: ['./product-row.component.scss']
})
export class ProductRowComponent implements OnInit {
  @Input() product: Offer;
  @Input() index: number;
  @Output() deleteEventEmitter = new EventEmitter();
  @Select(AppState.getUser)
  user: Observable<User>;
  storedUser: User;

  slides: string[];

  constructor(
    private configService: ConfigService, 
    private dialog: MatDialog,
    private toast: ToastrService,
    private offerService: OfferService
  ) { }

  ngOnInit(): void {
    this.user.subscribe(response => this.storedUser = response);
  }

  getBase64Prefix(): string {
    return this.configService.BASE64_PHOTO_PREFIX;
  }

  updateProduct(){
    const dialogRef = this.dialog.open(CreateProductDialogComponent, {data: this.product});

    dialogRef.afterClosed().subscribe(res => {
      if(res){
        this.offerService.updateOffer(this.product).subscribe(response => 
          this.toast.success('Izmenjen proizvod.')
        )
      }
    });
}

deleteProduct(){
  this.offerService.deleteOffer(this.product.id).subscribe(response => {
    this.deleteEventEmitter.emit();
  });
}

viewReviews(id: number) {
  const dialogRef = this.dialog.open(ViewReviewsComponent, {data: id});
}

}
