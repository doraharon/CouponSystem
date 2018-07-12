import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import 'rxjs/add/operator/map';
import { Customer } from '../common/Customer';
import swal from 'sweetalert2';
@Component({
  selector: 'app-get-customer',
  templateUrl: './get-customer.component.html',
  styleUrls: ['./get-customer.component.css']
})
export class GetCustomerComponent implements OnInit {
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
      }
    )
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
      },
      function(err)
      {
        swal({
          type: 'error',
          title: err._body,
        })
      }
    )
  }
}
}
