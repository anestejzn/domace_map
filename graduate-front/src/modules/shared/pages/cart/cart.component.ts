import { Component, OnInit } from '@angular/core';
import { Select, Store } from '@ngxs/store';
import { Observable } from 'rxjs';
import { AppState } from '../../state/app.state';
import { Item } from '../../model/item';
import { CartDataSource } from '../../model/cart-data-source';
import { MinusCartNumber, RemoveFromCart, UpdateCol } from '../../actions/app.action';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  dataSource: CartDataSource;
  @Select(AppState.getCart)
  cartItems: Observable<Item[]>;
  storedCartItems: Item[];
  displayedColumns: string[] = ['name', 'household', 'colForPrice', 'col', 'price', 'remove'];
  constructor(private store: Store, private router: Router) { }

  ngOnInit(): void {
    this.cartItems.subscribe(response => 
      {
        this.storedCartItems = response;
        this.dataSource = new CartDataSource(response);
      }
    );
  }

  updateCurrentValue(event, id: number){
    this.store.dispatch(new UpdateCol(id, event));
  }

  removeItem(id: number){
    this.store.dispatch(new RemoveFromCart(id));
    this.store.dispatch(new MinusCartNumber());
    this.cartItems.subscribe(response => 
      {
        this.storedCartItems = response;
        this.dataSource = new CartDataSource(response);
      }
    );
  }

  calculateTotalPrice() {
    let sum = 0;
    for(const item of this.storedCartItems){
      sum += item.col * item.price;
    }

    return sum;
  }

  confirmOrder(){
    this.router.navigate(['/map/order']);
  }

}
