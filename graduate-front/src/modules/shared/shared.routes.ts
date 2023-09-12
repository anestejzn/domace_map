import {Routes} from "@angular/router";
import { HomeComponent } from "./pages/home/home.component";
import { CartComponent } from "./pages/cart/cart.component";
import { FinishOrderComponent } from "./pages/finish-order/finish-order.component";
import { OrdersComponent } from "./pages/orders/orders.component";
import { OrderComponent } from "./pages/order/order.component";
import { HouseholdComponent } from "./pages/household/household.component";
import { RoleGuard } from "./guards/role/role.guard";


export const SharedRoutes: Routes = [
  {
    path: "home",
    pathMatch: "full",
    component: HomeComponent,
  },
  {
    path: "cart",
    pathMatch: 'full',
    component: CartComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: "ROLE_USER"}
  },
  {
    path: "order",
    pathMatch: 'full',
    component: FinishOrderComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: "ROLE_USER"}
  },
  {
    path: 'orders',
    pathMatch: 'full',
    component: OrdersComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: "ROLE_USER"}
  },
  {
    path: 'order/:id',
    pathMatch: 'full',
    component: OrderComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: "ROLE_USER"}
  },
  {
    path: 'household',
    pathMatch: 'full',
    component: HouseholdComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: "ROLE_USER"}
  },
  {
    path: 'household/:id',
    pathMatch: 'full',
    component: HouseholdComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: "ROLE_USER|ROLE_ADMIN"}
  }
 
]
