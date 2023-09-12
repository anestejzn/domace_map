import { Address } from "./address";
import { Household } from "./household";
import { Offer } from "./offer";
import { RegularUser } from "./regular-user";

export interface OrderRequest {
    userId: number;
    address: Address;
    phoneNumber: string;
    items: OrderItem[];
};

export interface OrderItem {
    offerId: number;
    quantity: number;
    price: number;
};

export interface Order {
    id: number;
    dateTime: Date;
    sentAt: Date;
    deliveredAt: Date;
    cancelled: boolean;
    cancelReason: string;
    user: RegularUser;
    household: Household;
    addressOrder: Address;
    phoneNumber: string;
    offerOrders: OfferOrder[];
};

export interface OfferOrder {
    id: number;
    offer: Offer;
    quantity: number;
    price: number;
    rated: boolean;
};