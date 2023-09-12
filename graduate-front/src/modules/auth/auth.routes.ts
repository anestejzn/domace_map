import { Routes } from "@angular/router";
import { RegistrationComponent } from "./pages/registration/registration.component";
import { LoginComponent } from "./pages/login/login.component";
import { SuccessfullVerificationComponent } from "./pages/successfull-verification/successfull-verification.component";
import { VerifyComponent } from "./pages/verify/verify.component";
import { UnauthGuard } from "../shared/guards/unauth/unauth.guard";

export const AuthRoutes: Routes = [
  {
    path: "login",
    pathMatch: "full",
    component: LoginComponent,
    canActivate: [UnauthGuard]
  },
  {
    path: "register",
    pathMatch: "full",
    component: RegistrationComponent,
    canActivate: [UnauthGuard]
  },
  {
    path: "verify/:id",
    pathMatch: "full",
    component: VerifyComponent,
    canActivate: [UnauthGuard] 
  },
  {
    path: "successfull-verification",
    pathMatch: "full",
    component: SuccessfullVerificationComponent,
    canActivate: [UnauthGuard]
  }
]