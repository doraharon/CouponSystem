import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Customer } from '../common/Customer';
import { DataService } from '../../services/data.service';
import swal from 'sweetalert2';
@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {
  public customer : Customer = new Customer ();
  public selectedId:number;
  public selectedName:string;
  constructor(private _data : DataService) { }

  ngOnInit() {
  }
  public getCustomerById()
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
    this._data.getCustomerById(this.selectedId).subscribe(
      function(customer)
      {
        self.customer = customer;
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
  public getCustomerByName()
  {
    if (this.selectedName == null || this.selectedName.trim().length == 0)
    { 
      swal({
        type: 'error',
        title:'you have to put a name first!',
        })
    }
    else
    {
    var self = this;
    this._data.getCustomerByName(this.selectedName).subscribe(
      function(customer)
      {
        self.customer = customer;
        self.selectedId = customer.id;
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
  public updateCustomer()
  {
    this._data.updateCustomer(this.customer);
  }
}
