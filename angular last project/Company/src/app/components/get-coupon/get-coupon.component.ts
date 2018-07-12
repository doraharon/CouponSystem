import { Component, OnInit } from '@angular/core';
import { Coupon } from '../common/Coupon';
import swal from 'sweetalert2';
import { DataService } from '../../services/data.service';
@Component({
  selector: 'app-get-coupon',
  templateUrl: './get-coupon.component.html',
  styleUrls: ['./get-coupon.component.css']
})
export class GetCouponComponent implements OnInit {
  public coupon : Coupon = new Coupon ();

  public selectedId:number;
  public selectedTitle:string;
  constructor(private _data : DataService) { }

  ngOnInit() {
  }
  public getCouponById()
  {
    if (this.selectedId == undefined)
    { 
      swal({
        type: 'error',
        title:'you have to put an id first!',
        })
    }
    else
    {
    var self = this;
    this._data.getCouponById(this.selectedId).subscribe(
      function(coupon)
      {
        self.coupon = coupon;
      },
      function(err)
      {
        swal({
          type: 'error',
          title: err._body,
        })
      })
    }
  }
  public getCouponByTitle()
  {
    if (this.selectedTitle == null || this.selectedTitle.trim().length == 0)
    { 
      swal({
        type: 'error',
        title:'you have to put a name first!',
        })
      }
    else
    {
      var self = this;
      this._data.getCouponByTitle(this.selectedTitle).subscribe(
      function(coupon)
      {
        self.coupon = coupon;
      },
      function(err)
      {
        swal({
          type: 'error',
          title: err._body,
        })
      })
    }
  }
}
