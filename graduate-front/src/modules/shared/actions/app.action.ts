import { Household } from "../model/household";
import { Item } from "../model/item";
import { User } from "../model/user";
import { Notification } from "../model/notification";

export class GetUser {
    static readonly type = '[User] Get';
}

export class ClearStore {
    static readonly type = '[App] ClearStore';
}

export class AddUser {
    static readonly type = '[User] Add';
  
    constructor(public payload: User) {}
}

export class AddHousehold {
    static readonly type = '[Household] Add';
  
    constructor(public payload: Household) {}
}

export class RemoveHousehold {
    static readonly type = '[Household] Remove';
}

export class UpdateCartNumber {
    static readonly type = '[CartNumber] Update';
}

export class MinusCartNumber {
    static readonly type = '[CartNumber] Minus';
}

export class RemoveCartNumber {
    static readonly type = '[CartNumber] Remove';
}

export class RemoveCart {
    static readonly type = '[Cart] Remove';
}

export class RemoveFromCart {
    static readonly type = '[Cart] RemoveFrom';
  
    constructor(public id: number) {}
}

export class AddToCart {
    static readonly type = '[Cart] AddTo';
  
    constructor(public payload: Item) {}
}

export class UpdateCol {
    static readonly type = '[Cart] UpdateCol';
  
    constructor(public id: number, private col: number) {}
}

export class UpdateNotificationsNumber {
    static readonly type = '[NotificationsNumber] Update';
}


export class RemoveNotificationsNumber {
    static readonly type = '[NotificationsNumber] Remove';
}


export class AddNotification {
    static readonly type = '[Notification] Add';
  
    constructor(public payload: Notification) {}
}

export class ReadAllNotifications {
    static readonly type = '[Notifications] Read';
}

export class AddNotifications {
    static readonly type = '[Notifications] Add';
  
    constructor(public payload: Notification[]) {}
}




  