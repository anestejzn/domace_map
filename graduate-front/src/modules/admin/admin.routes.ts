import {Routes} from "@angular/router";
import { HouseholdsComponent } from "./pages/households/households.component";
import { AllReviewsComponent } from "./pages/all-reviews/all-reviews.component";
import { RoleGuard } from "../shared/guards/role/role.guard";


export const AdminRoutes: Routes = [
  {
      path: "households",
      pathMatch: "full",
      component: HouseholdsComponent,
      canActivate: [RoleGuard],
      data: {expectedRoles: "ROLE_ADMIN"}
  },
  {
    path: "reviews",
    pathMatch: "full",
    component: AllReviewsComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: "ROLE_ADMIN"}
  }
 
]
