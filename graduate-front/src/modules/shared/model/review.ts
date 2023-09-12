import { Offer } from "./offer";

export interface Review {
    text: string;
    rate: number;
    date?: Date;
    userName?: string;
    id?: number;
    offerId?: number;
    userId?: number;
    offerOrderId?: number;
    pageNumber?: number;
    pageSize?: number;
    offer?: Offer;
    householdName?: string;
    householdCity?: string;
}