import { Role } from "./role";

export interface User {
  id?: number;
  email: string;
  name: string;
  surname: string;
  country: string;
  city: string;
  role: Role;
}
 