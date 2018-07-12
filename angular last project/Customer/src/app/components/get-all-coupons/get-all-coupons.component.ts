import { Component, OnInit } from '@angular/core';
import { Coupon } from '../common/Coupon';
import { DataService } from '../../services/data.service';
@Component({
  selector: 'app-get-all-coupons',
  templateUrl: './get-all-coupons.component.html',
  styleUrls: ['./get-all-coupons.component.css']
})
export class GetAllCouponsComponent implements OnInit {
  public _coupons : Coupon[] = [];
  constructor(private _data : DataService) {
    this.getCoupons();
   }

  ngOnInit() {
  }
  public getCoupons ()
  {
    var self = this;
    this._data.getCoupons().subscribe(
      function(coupons)
      {
        self._coupons = coupons;
      })
  }
}

