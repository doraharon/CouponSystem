import { Component, OnInit } from '@angular/core';
import { Coupon } from '../common/Coupon';
import { DataService } from '../../services/data.service';
@Component({
  selector: 'app-search-purchase-coupons',
  templateUrl: './search-purchase-coupons.component.html',
  styleUrls: ['./search-purchase-coupons.component.css']
})
export class SearchPurchaseCouponsComponent implements OnInit {
  public _coupons : Coupon[] = [];
  public selectedType : string;
  public selectedPrice : number;
  constructor(private _data : DataService) { }

  ngOnInit() {
  }

  public getPurchsedCouponsByType()
  {
    var self = this;
    this._data.getCouponsByType(this.selectedType).subscribe(
    function(coupons)
    {
      self._coupons = coupons;
    }) 
  }
  public getPurchsedCouponsByPrice ()
  {
    var self = this;
    this._data.getCouponsByPrice(this.selectedPrice).subscribe(
    function(coupons)
    {
      self._coupons = coupons;
    })
  }
}