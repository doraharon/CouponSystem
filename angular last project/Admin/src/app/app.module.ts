import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { DataService } from './services/data.service';

import { AppComponent } from './app.component';
import { CreateCompanyComponent } from './components/create-company/create-company.component';
import { CreateCustomerComponent } from './components/create-customer/create-customer.component';
import { MenuBarComponent } from './components/menu-bar/menu-bar.component';
import { GetCompanyComponent } from './components/get-company/get-company.component';
import { GetAllCompaniesComponent } from './components/get-all-companies/get-all-companies.component';
import { GetAllCustomersComponent } from './components/get-all-customers/get-all-customers.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { GetCustomerComponent } from './components/get-customer/get-customer.component';
import { RemoveCompanyComponent } from './components/remove-company/remove-company.component';
import { RemoveCustomerComponent } from './components/remove-customer/remove-customer.component';
import { UpdateCompanyComponent } from './components/update-company/update-company.component';
import { UpdateCustomerComponent } from './components/update-customer/update-customer.component';




@NgModule({
  declarations: [
    AppComponent,
    CreateCompanyComponent,
    CreateCustomerComponent,
    MenuBarComponent,
    GetCompanyComponent,
    GetAllCompaniesComponent,
    GetAllCustomersComponent,
    WelcomeComponent,
    GetCustomerComponent,
    RemoveCompanyComponent,
    RemoveCustomerComponent,
    UpdateCompanyComponent,
    UpdateCustomerComponent,
    
    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path: 'company/create',
        component: CreateCompanyComponent
      },
      {
        path: 'customer/create',
        component: CreateCustomerComponent
      },
      {
        path: 'company/get',
        component: GetCompanyComponent
      },
      {
        path: 'customer/get',
        component: GetCustomerComponent
      },
      {
        path: 'company/get/all',
        component: GetAllCompaniesComponent
      },
      {
        path: 'customer/get/all',
        component: GetAllCustomersComponent
      },
      {
        path: 'company/remove',
        component: RemoveCompanyComponent
      },
      {
        path: 'customer/remove',
        component: RemoveCustomerComponent
      },
      {
        path: 'company/update',
        component: UpdateCompanyComponent
      },
      {
        path: 'customer/update',
        component: UpdateCustomerComponent
      },
      {  
        path: '',
        component: WelcomeComponent
      }
    
    ])
  ],
  providers: [DataService,{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
