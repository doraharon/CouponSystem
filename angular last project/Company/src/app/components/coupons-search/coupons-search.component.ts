import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { Coupon } from '../common/Coupon';
import { DataService } from '../../services/data.service';
@Component({
  selector: 'app-coupons-search',
  templateUrl: './coupons-search.component.html',
  styleUrls: ['./coupons-search.component.css']
})
export class CouponsSearchComponent implements OnInit {
  public _coupons : Coupon[] = [];
  public selectedType:string;
  public selectedPrice:number;
  public selectedEndDate:Date;
  constructor(private _data : DataService) { }

  ngOnInit() {
  }
  public getCouponsByType ()
  {
    var self = this;
    this._data.getCouponsByType(this.selectedType).subscribe(
    function(coupons)
    {
      self._coupons = coupons;
    })
  }
  public getCouponsByPrice ()
  {
    var self = this;
    this._data.getCouponsByPrice(this.selectedPrice).subscribe(
    function(coupons)
    {
      self._coupons = coupons;
    })
  }  
  public getCouponsByEndDate ()
  {
    var self = this;
    this._data.getCouponsByEndDate(this.selectedEndDate).subscribe(
    function(coupons)
    {
      self._coupons = coupons;
    })
  }
}