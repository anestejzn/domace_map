import { Role } from "src/modules/shared/model/role";

export interface RegistrationResponse {
    id: number;
    email: string;
    password: string;
    name: string;
    surname: string;
    role: Role;
    lockedUntil: Date;
    verificationId: number;
};