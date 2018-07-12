import { Component, OnInit } from '@angular/core';
import 'rxjs/add/operator/map';
import { Customer } from '../common/Customer';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-get-all-customers',
  templateUrl: './get-all-customers.component.html',
  styleUrls: ['./get-all-customers.component.css']
})
export class GetAllCustomersComponent implements OnInit {
  public _customers : Customer[] = []; 
  constructor(private _data : DataService) {
    this.getCustomers();
   }
  ngOnInit() {
  }
  public getCustomers ()
  {
    var self = this;
    this._data.getCustomers().subscribe(
      function(customers)
      {
        self._customers = customers;
      }
    )
  }
}
