import { Household } from "./household";
import { Role } from "./role";
import { User } from "./user";

export interface RegularUser extends User{
    household: Household;
}