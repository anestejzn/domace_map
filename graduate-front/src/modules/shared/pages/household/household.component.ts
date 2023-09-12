import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Select } from '@ngxs/store';
import { Observable } from 'rxjs';
import { Household } from 'src/modules/shared/model/household';
import { AppState } from 'src/modules/shared/state/app.state';
import { HouseholdService } from '../../service/household-service/household-service';
import { User } from '../../model/user';

@Component({
  selector: 'app-household',
  templateUrl: './household.component.html',
  styleUrls: ['./household.component.scss']
})
export class HouseholdComponent implements OnInit {
  @Select(AppState.getHousehold)
  household: Observable<Household>;
  storedHousehold: Household;
  @Select(AppState.getUser)
  user: Observable<User>;
  storedUser: User;

  constructor(private route: ActivatedRoute, private householdService: HouseholdService) { }

  ngOnInit(): void {
    this.user.subscribe(response => this.storedUser = response);

    this.household.subscribe(response =>
      { 
        if(response === null) {
          this.route.params
            .subscribe(params => {
            const id = params['id'];
            if(id !== undefined){
              this.householdService.getHousehold(id).subscribe(
                response => this.storedHousehold = response
              )
            }
          });
        }
      this.storedHousehold = response;
      console.log(response);
    });
  }

}
