import { Component, OnInit } from '@angular/core';
import { Company } from '../common/Company';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit {
  public company : Company = new Company();
  constructor(private _data : DataService) { }

  ngOnInit() {
  }
  public createCompany()
  {
   this._data.createCompany(this.company);
  }
}
