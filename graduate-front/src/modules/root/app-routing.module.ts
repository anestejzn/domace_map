import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RootLayoutComponent } from './components/root-layout/root-layout.component';

const routes: Routes = [
  {
    path: "map",
    component: RootLayoutComponent,
    children: [
      {
        path: "auth",
        loadChildren: () => 
          import("./../auth/auth.module").then((m) => m.AuthModule)
      },
      {
        path: "",
        loadChildren: () => 
        import("./../shared/shared.module").then((m) => m.SharedModule)
      },
      {
        path: "user",
        loadChildren: () => 
        import("./../user/user.module").then((m) => m.UserModule)
      },
      {
        path: "admin",
        loadChildren: () => 
        import("./../admin/admin.module").then((m) => m.AdminModule)
      }
    ]
  },
  {
    path: "",
    redirectTo: "/map/home",
    pathMatch: "full",
  },
  { 
    path: "**", 
    redirectTo: "/map/home"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
