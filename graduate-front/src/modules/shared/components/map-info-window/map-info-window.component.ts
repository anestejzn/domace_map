import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { AddToCart, UpdateCartNumber } from '../../actions/app.action';
import { Select, Store } from '@ngxs/store';
import { Item } from '../../model/item';
import { AppState } from '../../state/app.state';
import { Household } from '../../model/household';
import { Observable } from 'rxjs';
import { User } from '../../model/user';

@Component({
  selector: 'app-map-info-window',
  templateUrl: './map-info-window.component.html',
  styleUrls: ['./map-info-window.component.scss']
})
export class MapInfoWindowComponent implements OnInit {
  @Input() infoContent: any;
  value;
  @Select(AppState.getHousehold)
  household: Observable<Household>;
  storedHousehold: Household;
  @Select(AppState.getUser)
  user: Observable<User>;
  storedUser: User;
  constructor(private store: Store) {
   }

  ngOnInit(): void {
    this.household.subscribe(
      response => this.storedHousehold = response
    );

    this.user.subscribe(
      response => this.storedUser = response
    );


  }

  updateCurrentValue(event){
    this.value = event;
  }

  addToCart(){

    const item: Item = {
      household: this.infoContent.household,
      householdId: this.infoContent.householdId,
      name: this.infoContent.name,
      id: this.infoContent.id,
      colForPrice: this.infoContent.colForPrice,
      price: this.infoContent.price,
      col: this.value === undefined ? 1 : this.value
    };
    this.store.dispatch(new AddToCart(item));
    this.store.dispatch(new UpdateCartNumber());
  }


  

}
