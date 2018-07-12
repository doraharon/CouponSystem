import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { DataService } from './services/data.service';

import { AppComponent } from './app.component';
import { CreateCouponComponent } from './components/create-coupon/create-coupon.component';
import { MenuBarComponent } from './components/menu-bar/menu-bar.component';
import { GetAllCouponsComponent } from './components/get-all-coupons/get-all-coupons.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { GetCouponComponent } from './components/get-coupon/get-coupon.component';
import { CouponsSearchComponent } from './components/coupons-search/coupons-search.component';
import { RemoveCouponComponent } from './components/remove-coupon/remove-coupon.component';
import { UpdateCouponComponent } from './components/update-coupon/update-coupon.component';



@NgModule({
  declarations: [
    AppComponent,
    CreateCouponComponent,
    MenuBarComponent,
    GetAllCouponsComponent,
    WelcomeComponent,
    GetCouponComponent,
    CouponsSearchComponent,
    RemoveCouponComponent,
    UpdateCouponComponent,
  
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path: '',
        component: WelcomeComponent
      },
      {
        path: 'coupon/create',
        component: CreateCouponComponent
      },
      {
        path: 'coupon/get/all',
        component: GetAllCouponsComponent
      },
      {
        path: 'coupon/get',
        component: GetCouponComponent
      },
      {
        path: 'coupon/get/by',
        component: CouponsSearchComponent
      },
      {
        path: 'coupon/remove',
        component: RemoveCouponComponent
      },
      {
        path: 'coupon/update',
        component: UpdateCouponComponent
      },
    ])
  ],
  providers: [DataService,{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
