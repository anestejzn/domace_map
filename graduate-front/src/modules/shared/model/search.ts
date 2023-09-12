import { Household } from "./household";
import { Offer } from "./offer";

export interface Search{
    lon?: number;
    lat?: number;
    productType?: string[];
    offer?: Offer;
    household?: Household;
};