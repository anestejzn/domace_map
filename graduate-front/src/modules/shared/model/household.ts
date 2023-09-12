import { Address } from "./address";
import { Offer } from "./offer";

export interface Household{
    id?: number;
    name: string;
    registrationNumber: string;
    phoneNumber: string;
    userId?: number;
    address: Address;
    offers?: Offer[];
    pageNumber?: number;
    pageSize?: number;
}