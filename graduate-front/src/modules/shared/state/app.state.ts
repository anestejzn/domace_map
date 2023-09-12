import { State, Action, StateContext, Selector, Select } from '@ngxs/store';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { User } from '../model/user';
import { Household } from '../model/household';
import { AddHousehold, AddNotification, AddNotifications, AddToCart, AddUser, ClearStore, MinusCartNumber, ReadAllNotifications, RemoveCart, RemoveCartNumber, RemoveFromCart, RemoveHousehold, RemoveNotificationsNumber, UpdateCartNumber, UpdateCol, UpdateNotificationsNumber } from '../actions/app.action';
import { Item } from '../model/item';
import { Notification } from '../model/notification';

export class AppStateModel {
  user: User;
  household: Household;
  cartNumber: number;
  notificationsNumber: number;
  cart: Item[];
  notifications: Notification[];
}

@State<AppStateModel>({
  name: 'currentState',
  defaults: {
    user: null,
    household: null,
    cartNumber: null,
    notificationsNumber: null,
    cart: [],
    notifications: []
  },
})
@Injectable()
export class AppState {
  @Selector()
  static getUser(state: AppStateModel) {
    return state.user;
  }
  @Select() appState$: Observable<AppState>;

  @Selector()
  static getHousehold(state: AppStateModel) {
    return state.household;
  }

  @Selector()
  static getCartNumber(state: AppStateModel) {
    return state.cartNumber;
  }

  @Selector()
  static getNotificationsNumber(state: AppStateModel) {
    return state.notificationsNumber;
  }

  @Selector()
  static getCart(state: AppStateModel) {
    return state.cart;
  }

  @Selector()
  static getNotifications(state: AppStateModel) {
    return state.notifications;
  }


  @Action(AddUser)
  addUser(
    { getState, setState }: StateContext<AppStateModel>,
    { payload }: AddUser
  ) {
    const state = getState();
    setState({
      ...state,
      user: payload,
    });
  }

  @Action(AddHousehold)
  addHousehold(
    { getState, setState }: StateContext<AppStateModel>,
    { payload }: AddHousehold
  ) {
    const state = getState();
    setState({
      ...state,
      household: payload,
    });
  }

  @Action(RemoveHousehold)
  removeHousehold(
    { getState, setState }: StateContext<AppStateModel>,
  ) {
    const state = getState();

    setState({
      ...state,
      household: null,
    });
  };

  @Action(ClearStore)
  clearStore({
    getState,
    setState,
  }: StateContext<AppStateModel>) {
    const state = getState();
    setState({
      ...state,
      user: null,
      household: null,
      cart: [],
      cartNumber: null,
      notificationsNumber: null,
      notifications: null
    });
  }

  @Action(UpdateCartNumber)
  update(
    { getState, setState }: StateContext<AppStateModel>,
  ) {
    const state = getState();
    const cartNumber = state.cartNumber !== null ? state.cartNumber + 1 : 1;

    setState({
      ...state,
      cartNumber: cartNumber,
    });
  };

  @Action(MinusCartNumber)
  minus(
    { getState, setState }: StateContext<AppStateModel>,
  ) {
    const state = getState();
    let cartNumber = state.cartNumber - 1;
    cartNumber = cartNumber === 0 ? null : cartNumber;

    setState({
      ...state,
      cartNumber: cartNumber,
    });
  };

  @Action(RemoveCartNumber)
  removeCartNumber(
    { getState, setState }: StateContext<AppStateModel>,
  ) {
    const state = getState();

    setState({
      ...state,
      cartNumber: null,
    });
  }

  @Action(RemoveCart)
  removeCart(
    { getState, setState }: StateContext<AppStateModel>,
  ) {
    const state = getState();

    setState({
      ...state,
      cart: [],
    });
  }

  @Action(RemoveFromCart)
  removeFromCart(
    { getState, setState }: StateContext<AppStateModel>,
    {id} : {id: number}
  ) {
    const state = getState();
    const index = state.cart.indexOf(state.cart.find(item => item.id === id));
    state.cart.splice(index, 1);

    setState({
      ...state,
      cart: state.cart,
    });
  }


  @Action(AddToCart)
  addToCart(
    { getState, setState }: StateContext<AppStateModel>,
    {payload} : {payload:Item}
  ) {
    const state = getState();
    let newCart = state.cart ?? [];
    newCart.push(payload);

    setState({
      ...state,
      cart: newCart,
    });
  }

  @Action(UpdateCol)
  updateCol(
    { getState, setState }: StateContext<AppStateModel>,
    {id, col} : {id:number, col: number}
  ) {
    const state = getState();
    state.cart.find(item => item.id === id).col = col;

    setState({
      ...state,
      cart: state.cart,
    });
  }

  @Action(UpdateNotificationsNumber)
  updateNotif(
    { getState, setState }: StateContext<AppStateModel>,
  ) {
    const state = getState();
    const notificationsNumber = state.notificationsNumber !== null ? state.notificationsNumber + 1 : 1;
    console.log('eee');
    setState({
      ...state,
      notificationsNumber: notificationsNumber,
    });
  };

  @Action(RemoveNotificationsNumber)
  removeNotif(
    { getState, setState }: StateContext<AppStateModel>,
  ) {
    const state = getState();

    setState({
      ...state,
      notificationsNumber: null,
    });
  };

  @Action(AddNotification)
  addNotification(
    { getState, setState }: StateContext<AppStateModel>,
    {payload} : {payload:Notification}
  ) {
    const state = getState();
    let newList = state.notifications ?? [];
    newList.push(payload);

    setState({
      ...state,
      notifications: newList,
    });
  }

  @Action(AddNotifications)
  addNotifications(
    { getState, setState }: StateContext<AppStateModel>,
    {payload} : {payload:Notification[]}
  ) {
    const state = getState();

    setState({
      ...state,
      notifications: payload,
    });
  }

  @Action(ReadAllNotifications)
  readAllNotifications(
    { getState, setState }: StateContext<AppStateModel>
  ) {
    const state = getState();
    let notifications = state.notifications ?? [];
    for(let notif of notifications){
      notif.read = true;
    }

    setState({
      ...state,
      notifications: notifications,
    });
  }
}
