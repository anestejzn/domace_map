import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { GoogleMapsModule } from '@angular/google-maps';
import { SharedRoutes } from './shared.routes';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { MapPageComponent } from './pages/map-page/map-page.component';
import { HomeComponent } from './pages/home/home.component';
import { SearchComponent } from './components/search/search.component';
import { AutocompleteMapInputComponent } from './components/autocomplete-map-input/autocomplete-map-input.component';
import { GooglePlaceModule } from 'ngx-google-places-autocomplete';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthInterceptor } from './interceptors/auth-interceptor';
import { PlusMinusInputComponent } from './components/plus-minus-input/plus-minus-input.component';
import { MapInfoWindowComponent } from './components/map-info-window/map-info-window.component';
import { CartComponent } from './pages/cart/cart.component';
import { FinishOrderComponent } from './pages/finish-order/finish-order.component';
// import { AuthInterceptor } from './interceptors/auth-interceptor';
import { DateFormatPipe } from './pipes/date-format.pipe';
import { OrdersComponent } from './pages/orders/orders.component';
import { OrderComponent } from './pages/order/order.component';
import { CancelOrderDialogComponent } from './components/cancel-order-dialog/cancel-order-dialog.component';
import { RateOrderDialogComponent } from './components/rate-order-dialog/rate-order-dialog.component';
import { RejectOrderDialogComponent } from './components/reject-order-dialog/reject-order-dialog.component';
import { ViewReviewsComponent } from './components/view-reviews/view-reviews.component';
import { HouseholdComponent } from './pages/household/household.component';
import { HouseholdRegistrationComponent } from './components/household-registration/household-registration.component';
import { ShowProductsComponent } from './components/products/show-products/show-products.component';
import { CreateProductDialogComponent } from './components/products/create-product-dialog/create-product-dialog.component';
import { ProductRowComponent } from './components/products/product-row/product-row.component';
import { HouseholdInfoComponent } from './components/household-info/household-info.component';
import { PaginationComponent } from './components/pagination/pagination.component';


@NgModule({
  declarations: [
    MapPageComponent,
    HomeComponent, 
    SearchComponent, 
    AutocompleteMapInputComponent, 
    PlusMinusInputComponent, 
    MapInfoWindowComponent, 
    CartComponent, 
    FinishOrderComponent,
    DateFormatPipe,
    OrdersComponent,
    OrderComponent,
    CancelOrderDialogComponent,
    RateOrderDialogComponent,
    RejectOrderDialogComponent,
    ViewReviewsComponent,
    HouseholdComponent,
    HouseholdRegistrationComponent,
    ShowProductsComponent,
    CreateProductDialogComponent,
    ProductRowComponent,
    HouseholdInfoComponent,
    PaginationComponent
  ],
  exports: [
    DateFormatPipe
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    MaterialModule,
    GoogleMapsModule,
    GooglePlaceModule,
    ReactiveFormsModule,
    FormsModule,
    ToastrModule.forRoot({
      positionClass: 'toast-bottom-left',
      preventDuplicates: true,
      closeButton: true,
    }),
    RouterModule.forChild(SharedRoutes)
  ],
  providers:[
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ]
})
export class SharedModule { }
