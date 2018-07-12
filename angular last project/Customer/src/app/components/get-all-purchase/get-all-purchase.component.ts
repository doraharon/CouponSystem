import { Component, OnInit } from '@angular/core';
import { Coupon } from '../common/Coupon';
import { DataService } from '../../services/data.service';
@Component({
  selector: 'app-get-all-purchase',
  templateUrl: './get-all-purchase.component.html',
  styleUrls: ['./get-all-purchase.component.css']
})
export class GetAllPurchaseComponent implements OnInit {
  public _coupons : Coupon[] = [];
  constructor(private _data : DataService) {
    this.getPurchasedCoupons();
   }

  ngOnInit() {
  }
  public getPurchasedCoupons()
  {
    var self = this;
    this._data.getPurchasedCoupons().subscribe(
      function(coupons)
      {
        self._coupons = coupons;
      })
  }
}
