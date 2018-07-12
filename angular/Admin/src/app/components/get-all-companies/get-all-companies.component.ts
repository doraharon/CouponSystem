import { Component, OnInit } from '@angular/core';
import { Company } from '../common/Company';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-get-all-companies',
  templateUrl: './get-all-companies.component.html',
  styleUrls: ['./get-all-companies.component.css']
})
export class GetAllCompaniesComponent implements OnInit {
  public _companies : Company[] = []; 
  constructor(private _data : DataService) {
    this.getCompanies();
   }

  ngOnInit() {
  }
  public getCompanies ()
  {
    var self = this;
    this._data.getCompanies().subscribe(
       function(companies)
      {
        self._companies = companies;
      }
    )
  }
}
