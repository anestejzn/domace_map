import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Household } from 'src/modules/shared/model/household';
import { HouseholdService } from 'src/modules/shared/service/household-service/household-service';

@Component({
  selector: 'app-households',
  templateUrl: './households.component.html',
  styleUrls: ['./households.component.scss']
})
export class HouseholdsComponent implements OnInit {

  households: Household[] = [];
  pageSize = 6;
  totalPages:number;
  currentPage = 0;

  householdsSubscription: Subscription;
  constructor(private householdService: HouseholdService) {
  }

  ngOnInit(): void {
    this.loadHouseholds();
  }

  loadHouseholds() {
    this.householdsSubscription = this.householdService
      .getHouseholdsWithPagination(this.currentPage, this.pageSize)
      .subscribe((response: Household[]) => {
        this.households = response;
        console.log(response);
        if(response.length > 0){
          this.totalPages = this.households[0].pageNumber;
        }
      });
  }

  changePage(newPage: number) {
    this.currentPage = newPage;
    this.loadHouseholds();
  }


  ngOnDestroy(): void {
    if (this.householdsSubscription) {
      this.householdsSubscription.unsubscribe();
    }
  }

}
