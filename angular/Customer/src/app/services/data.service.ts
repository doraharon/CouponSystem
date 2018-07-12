import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import swal from 'sweetalert2';
import { Coupon } from '../components/common/Coupon';

@Injectable()
export class DataService {

  constructor(private _http : Http) { }
    
  public getCoupons ()
  {
    return this._http.get('http://localhost:8080/customer/coupon/all').map (
      function(couponsResponse)
      {
        return couponsResponse.json();
      })
  }
  public getPurchasedCoupons ()
  {
    return this._http.get('http://localhost:8080/customer/purchase/all').map (
      function(couponsResponse)
      {
        return couponsResponse.json();
      })
  } 
  public getPurchasedCouponById (id : number)
  {
    return this._http.get(`http://localhost:8080/customer/purchase/${id}`).map (
      function(couponResponse)
      {
        return couponResponse.json();
      })
  }
  public getPurchasedCouponByTitle (title : string)
  {
    return this._http.get(`http://localhost:8080/customer/purchase/title/${title}`).map (
      function(couponResponse)
      {
        return couponResponse.json();
      })
  }
  public getCouponById (id : number)
  {
    return this._http.get(`http://localhost:8080/customer/coupon/${id}`).map (
      function(couponResponse)
      {
        return couponResponse.json();
      })
  }
  public getCouponByTitle (title : string)
  {
    return this._http.get(`http://localhost:8080/customer/coupon/title/${title}`).map (
      function(couponResponse)
      {
        return couponResponse.json();
      })
  }
  public purchaseCoupon(coupon : Coupon)
  {
    if (coupon.title == null || coupon.title == "")
    {
      swal({
        type: 'error',
        title: 'details are missing!',
        text: 'You have to find the coupon first',
      })
    }
    else
    {
      swal({
        title: 'Are you sure?',
        text: "You will be charged " + coupon.price + ' ₪'  ,
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
          if (result.value) {
            this._http.post('http://localhost:8080/customer/purchase', coupon).subscribe(function(response)
          {
            console.log(response);
          
      swal(
        'Good job!',
        'The Coupon ' + coupon.title + ' has been purchased successfully!',
        'success'
        )
      },
      function (err)
      {
        console.log(err);
        swal({
          type: 'error',
          title: err._body,
        })
      }
        )}  
      })
    }
  }
  public getCouponsByType (type : string)
  {
    return this._http.get(`http://localhost:8080/customer/purchase/type/${type}`).map (
      function(couponsResponse)
      {
        return couponsResponse.json();
      })
  }
  public getCouponsByPrice (price : number)
  {
    return this._http.get(`http://localhost:8080/customer/purchase/price/${price}`).map (
      function(couponsResponse)
      {
        return couponsResponse.json();
      })
  }
}
