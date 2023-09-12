import { Household } from "./household";
import { Review } from "./review";

export interface Offer{
    name: string;
    description: string;
    price: number;
    colForPrice: string;
    type: string;
    photos: string[];
    household?: Household;
    id?: number;
    averageRate?: number;
    householdId?: number;
    pageSize?: number;
    pageNumber?: number;
    reviews?: Review[];
}