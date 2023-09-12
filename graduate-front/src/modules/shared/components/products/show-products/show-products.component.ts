import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable, Subscription } from 'rxjs';
import { Offer } from 'src/modules/shared/model/offer';
import { CreateProductDialogComponent } from '../create-product-dialog/create-product-dialog.component';
import { OfferService } from 'src/modules/user/service/offer.service';
import { AppState } from 'src/modules/shared/state/app.state';
import { Select } from '@ngxs/store';
import { Household } from 'src/modules/shared/model/household';
import { RegularUser } from 'src/modules/shared/model/regular-user';
import { isNullOrUndef } from 'chart.js/dist/helpers/helpers.core';
import { User } from 'src/modules/shared/model/user';

@Component({
  selector: 'app-show-products',
  templateUrl: './show-products.component.html',
  styleUrls: ['./show-products.component.scss']
})
export class ShowProductsComponent implements OnInit {
  @Input() storedHousehold: Household;
  @Select(AppState.getUser)
  user: Observable<User>
  storedUser: User;

  products: Offer[];
  pageSize = 3;
  totalPages:number;
  currentPage = 0;

  usersSubscription: Subscription;
  constructor( private dialog: MatDialog, private offerService: OfferService) {
    this.products = [];
  }

  ngOnInit(): void {
    this.user.subscribe(response => this.storedUser = response);
    this.offerService
      .getOffersWithPagination(this.currentPage, this.pageSize, this.storedHousehold.id)
      .subscribe((response: Offer[]) => {
        this.products = response;
        console.log(response);
        if(this.products.length > 0){
          this.totalPages = this.products[0].pageNumber;
        }
      });
  }

  changePage(newPage: number) {
    
    this.currentPage = newPage;
    this.offerService
      .getOffersWithPagination(this.currentPage, this.pageSize, this.storedHousehold.id)
      .subscribe((response: Offer[]) => {
        this.products = response;
        if(this.products.length > 0){
          this.totalPages = this.products[0].pageNumber;
        }
      });
  }

  createNewProduct(){
    const dialogRef = this.dialog.open(CreateProductDialogComponent, {data: null});

    dialogRef.afterClosed().subscribe(res => {
      if (res) {
        this.loadProducts();
      }
    });
  }

  loadProducts(){
    this.offerService
    .getOffersWithPagination(this.currentPage, this.pageSize, this.storedHousehold.id)
    .subscribe((response: Offer[]) => {
      this.products = response;
      if(this.products.length > 0){
        this.totalPages = this.products[0].pageNumber;
      }
    });
  }

  ngOnDestroy(): void {
    if (this.usersSubscription) {
      this.usersSubscription.unsubscribe();
    }
  }

}
