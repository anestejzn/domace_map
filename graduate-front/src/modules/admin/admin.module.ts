import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { GoogleMapsModule } from '@angular/google-maps';
import { GooglePlaceModule } from 'ngx-google-places-autocomplete';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgxsReduxDevtoolsPluginModule } from '@ngxs/devtools-plugin';
import { NgxsLoggerPluginModule } from '@ngxs/logger-plugin';
import { NgxsStoragePluginModule } from '@ngxs/storage-plugin';
import { NgxsModule } from '@ngxs/store';
import { MaterialModule } from 'src/modules/material/material.module';
import { SharedModule } from 'src/modules/shared/shared.module';
import { AppState } from 'src/modules/shared/state/app.state';
import { HouseholdsComponent } from './pages/households/households.component';
import { AllReviewsComponent } from './pages/all-reviews/all-reviews.component';
import { AdminRoutes } from './admin.routes';
import { HouseholdRowComponent } from './components/household-row/household-row.component';
import { ReviewRowComponent } from './components/review-row/review-row.component';


@NgModule({
  declarations: [
    HouseholdsComponent,
    AllReviewsComponent,
    HouseholdRowComponent,
    ReviewRowComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    HttpClientModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    GoogleMapsModule,
    GooglePlaceModule,
    RouterModule.forChild(AdminRoutes),
    NgxsModule.forRoot([
      AppState
    ]),
    NgxsReduxDevtoolsPluginModule.forRoot(),
    NgxsLoggerPluginModule.forRoot(),
    NgxsStoragePluginModule.forRoot(),
  ]
})
export class AdminModule { }
