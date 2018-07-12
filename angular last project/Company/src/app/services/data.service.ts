import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import swal from 'sweetalert2';
import { Coupon } from '../components/common/Coupon';

@Injectable()
export class DataService {

  constructor(private _http : Http) { }

  public getCouponsByType (type : string)
  {
    return this._http.get(`http://localhost:8080/company/coupons/type/${type}`).map (
      function(couponsResponse)
      {
        return couponsResponse.json();
      })
  }
  public getCouponsByPrice (price : number)
  {
    return this._http.get(`http://localhost:8080/company/coupons/price/${price}`).map (
      function(couponsResponse)
      {
        return couponsResponse.json();
      })
  }
  public getCouponsByEndDate (endDate : Date)
  {
    return this._http.get(`http://localhost:8080/company/coupons/date/${endDate}`).map (
    function(couponsResponse)
    {
      return couponsResponse.json();
    })
  }
  public createCoupon(coupon : Coupon)
  { 
    if (coupon.title == null || coupon.amount == null || coupon.price == null || coupon.endDate == null
        || coupon.title == "" || coupon.price == 0 || coupon.amount == 0 )
    {
      swal({
        type: 'error',
        title: 'details are missing!',
        text: 'You have to fill title, amount > 0, price > 0 and end Date',
      })
    }
    else
    {
    this._http.post('http://localhost:8080//company/coupon', coupon).subscribe(function(response)
    {
      console.log(response);
      swal(
       'Good job!',
        'The Coupon ' + coupon.title +' has been added successfully!',
        'success'
     )},
    function(err)
    {
      console.log(err);
      swal({
        type: 'error',
        title: err._body,
        })
     })
   }
  }
  public getCoupons ()
  {
    return this._http.get('http://localhost:8080/company/coupons').map (
      function(couponsResponse)
      {
        return couponsResponse.json();
      })
  }
  public getCouponById (id : number)
  {
    return this._http.get(`http://localhost:8080/company/coupon/${id}`).map (
      function(couponResponse)
      {
        return couponResponse.json();
      })
  }
  public getCouponByTitle (title : string)
  {
    return this._http.get(`http://localhost:8080/company/coupon/title/${title}`).map (
      function(couponResponse)
      {
        return couponResponse.json();
      })
  }
  public removeCoupon(coupon : Coupon , id : number)
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
        text: "The Customer " + coupon.title + " will be delete for ever!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
          if (result.value) {
          this._http.delete(`http://localhost:8080/company/coupon/remove/${id}`).subscribe(function(response)
          {
            console.log(response); 
          })
      swal(
        'Deleted!',
        'The Coupon ' + coupon.title + ' has been deleted successfully!',
        'success'
        )}
    })
  } 
 }
public updateCoupon(coupon : Coupon)
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
        text: "All previous information of " + coupon.title + "will be lost!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, update it!'
      }) .then((result) => {
          if (result.value) {
           this._http.put(`http://localhost:8080/company/coupon`, coupon).subscribe(function(response)
           {
            console.log(response); 
           })
      swal(
      'Updated!',
      'The Coupon ' + coupon.title + ' has been updated successfully!',
      'success'
         )
       }
     })
   } 
  }
}


