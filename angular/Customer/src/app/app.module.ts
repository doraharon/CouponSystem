import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { DataService } from './services/data.service';

import { AppComponent } from './app.component';
import { MenuBarComponent } from './components/menu-bar/menu-bar.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { PurchaseCouponComponent } from './components/purchase-coupon/purchase-coupon.component';
import { GetPurchaseCouponComponent } from './components/get-purchase-coupon/get-purchase-coupon.component';
import { GetAllCouponsComponent } from './components/get-all-coupons/get-all-coupons.component';
import { GetAllPurchaseComponent } from './components/get-all-purchase/get-all-purchase.component';
import { SearchPurchaseCouponsComponent } from './components/search-purchase-coupons/search-purchase-coupons.component';


@NgModule({
  declarations: [
    AppComponent,
    MenuBarComponent,
    WelcomeComponent,
    PurchaseCouponComponent,
    GetPurchaseCouponComponent,
    GetAllCouponsComponent,
    GetAllPurchaseComponent,
    SearchPurchaseCouponsComponent
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
    path: 'coupon/purchase',
    component: PurchaseCouponComponent
  },
  {
    path: 'coupon/purchase/get',
    component: GetPurchaseCouponComponent
  },
  {
    path: 'coupon/get/all',
    component: GetAllCouponsComponent
  },
  {
    path: 'coupon/purchase/get/all',
    component: GetAllPurchaseComponent
  },
  {
    path: 'coupon/purchase/get/search',
    component: SearchPurchaseCouponsComponent
  },
  ])
  ],
  providers: [DataService,{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
