import { Component, OnInit } from '@angular/core';
import { Customer } from '../common/Customer';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {

  public customer : Customer = new Customer ();  

  constructor(private _data : DataService) { }

  ngOnInit() {
  }
  public createCustomer()
  {
   this._data.createCustomer(this.customer);
  }
}