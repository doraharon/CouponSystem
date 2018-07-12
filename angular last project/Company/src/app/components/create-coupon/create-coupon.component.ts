import { Component, OnInit } from '@angular/core';
import { Coupon } from '../common/Coupon';
import { DataService } from '../../services/data.service';
@Component({
  selector: 'app-create-coupon',
  templateUrl: './create-coupon.component.html',
  styleUrls: ['./create-coupon.component.css']
})
export class CreateCouponComponent implements OnInit {
  public coupon : Coupon = new Coupon (); 
  constructor(private _data : DataService) { 
  }

  ngOnInit() {
  }
  public img ()
  {
    switch (this.coupon.type)
    {
    case "RESTURANTS":
    this.coupon.image = "http://carrols.com/img/timelinr/3.jpg"
    break;
    case "ELECTRICITY": 
    this.coupon.image = "http://www.iconhot.com/icon/png/rumax-ip/256/computers.png"
    break;
    case "SPORTS": 
    this.coupon.image = "https://tiertensports.com/wp-content/uploads/2016/04/sports.jpg"
    break;
    case "CAMPING": 
    this.coupon.image = "http://discoverpak.com/wp-content/uploads/2016/03/Camping-1-256x256_c.jpg"
    break;
    case "TRAVELING": 
    this.coupon.image = "https://imgak.mmtcdn.com/pwa-hlp/assets/img/hlp/deals/ic-flight-2.jpg"
    break;
    

    }
    
  }
  public createCoupon()
  {
    this.img();
      this._data.createCoupon(this.coupon);
  }
}