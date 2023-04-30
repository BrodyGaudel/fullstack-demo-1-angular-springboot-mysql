import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {ListVmComponent} from "./components/list-vm/list-vm.component";
import {DetailsVmComponent} from "./components/details-vm/details-vm.component";
import {UpdateVmComponent} from "./components/update-vm/update-vm.component";
import {AddVmComponent} from "./components/add-vm/add-vm.component";
import {AppForbiddenComponent} from "./components/app-forbidden/app-forbidden.component";
import {VmGuard} from "./components/guard/vm.guard";
import {ListUserComponent} from "./components/list-user/list-user.component";
import {AddUserComponent} from "./components/add-user/add-user.component";

const routes: Routes = [
  {path: "login", component: LoginComponent},
  {path: "list-vm", component: ListVmComponent},
  {path: "details-vm/:id", component: DetailsVmComponent},
  {path: "update-vm/:id", component: UpdateVmComponent ,canActivate:[VmGuard]},
  {path: "create-vm", component: AddVmComponent ,canActivate:[VmGuard]},
  {path: "app-forbidden", component: AppForbiddenComponent},
  {path: "list-user", component: ListUserComponent, canActivate:[VmGuard]},
  {path: "add-user", component: AddUserComponent, canActivate:[VmGuard]},
  {path: "", redirectTo: "list-vm", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
