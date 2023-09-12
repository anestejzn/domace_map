import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Select, Store } from '@ngxs/store';
import { Options } from 'ngx-google-places-autocomplete/objects/options/options';
import { Observable } from 'rxjs';
import { User } from '../../model/user';
import { AppState } from '../../state/app.state';
import { Order, OrderItem, OrderRequest } from '../../model/order';
import { Item } from '../../model/item';
import { Address } from '../../model/address';
import { OrderService } from '../../service/order-service/order.service';
import { RemoveCart, RemoveCartNumber } from '../../actions/app.action';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-finish-order',
  templateUrl: './finish-order.component.html',
  styleUrls: ['./finish-order.component.scss']
})
export class FinishOrderComponent implements OnInit {
  showSpiner: boolean = false;
  done: boolean = false;
  @Select(AppState.getUser)
  user: Observable<User>;
  storedUser: User;
  @Select(AppState.getCart)
  cart: Observable<Item[]>;
  storedCart: Item[];
  address: Address = {
    street: '',
    number: '',
    city: ''
  }
  options: Options = new Options({
    bounds: undefined,
    fields: ['address_component', 'formatted_address', 'name', 'geometry'],
    strictBounds: false,
    componentRestrictions: { country: 'rs' },
  });

  orderForm = new FormGroup(
    {
      nameFormControl: new FormControl('', [
        Validators.required,
        Validators.pattern('[a-zA-Z -]*'),
      ]),
      surnameFormControl:  new FormControl('', [
        Validators.required,
        Validators.pattern('[a-zA-Z -]*'),
      ]),
      phoneFormControl: new FormControl('', [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(12),
        Validators.pattern('[0-9]*'),
      ]),
      emailFormControl:  new FormControl('', [
        Validators.required,
        Validators.email,
      ])
  })

  constructor(
    private orderService: OrderService, 
    private store: Store,
    private toast: ToastrService
    ) { }

  ngOnInit(): void {
    this.user.subscribe(response => {
      this.storedUser = response;
      this.orderForm.get('emailFormControl').setValue(response.email);
      this.orderForm.get('nameFormControl').setValue(response.name);
      this.orderForm.get('surnameFormControl').setValue(response.surname);
    });
    
    this.cart.subscribe(response => this.storedCart = response);
  }

  finishOrder(){
    let items = [];
    for(const item of this.storedCart){
      let orderItem: OrderItem = {
        offerId: item.id,
        quantity: item.col,
        price: item.price * item.col
      };
      items.push(orderItem);
    }

    let order: OrderRequest = {
      userId: this.storedUser.id,
      address: this.address,
      items: items,
      phoneNumber: this.orderForm.get('phoneFormControl').value
    };

    this.orderService.saveOrder(order).subscribe(
      response => {
      this.store.dispatch(new RemoveCart());
      this.store.dispatch(new RemoveCartNumber());
      this.done = true;
      },
      error => {
        this.toast.error(error.errror);
      }
    
    )
  }

  addressChange(address){
    this.address.street = address.address_components[0].short_name;
    this.address.number = address.address_components[1].short_name;
    this.address.city = address.address_components[2].short_name;
    // this.address.lon = address.geometry.location.lng();
    // this.address.lat = address.geometry.location.lat();
  }

}
