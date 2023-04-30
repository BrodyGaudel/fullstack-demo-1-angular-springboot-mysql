import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { ListVmComponent } from './components/list-vm/list-vm.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { DetailsVmComponent } from './components/details-vm/details-vm.component';
import { AddVmComponent } from './components/add-vm/add-vm.component';
import { UpdateVmComponent } from './components/update-vm/update-vm.component';
import { AppForbiddenComponent } from './components/app-forbidden/app-forbidden.component';
import { ListUserComponent } from './components/list-user/list-user.component';
import { AddUserComponent } from './components/add-user/add-user.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ListVmComponent,
    DetailsVmComponent,
    AddVmComponent,
    UpdateVmComponent,
    AppForbiddenComponent,
    ListUserComponent,
    AddUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
