import { Component, OnInit } from '@angular/core';
import { Company } from '../common/Company';
import { DataService } from '../../services/data.service';
import swal from 'sweetalert2';
@Component({
  selector: 'app-update-company',
  templateUrl: './update-company.component.html',
  styleUrls: ['./update-company.component.css']
})
export class UpdateCompanyComponent implements OnInit {
  public company : Company = new Company ();
  public selectedId:number;
  public selectedName:string;
  constructor(private _data : DataService) { }

  ngOnInit() {
  }
  public getCompanyById()
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
    this._data.getCompanyById(this.selectedId).subscribe(
      function(company)
      {
        self.company = company;
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

  public getCompanyByName()
  {
    if (this.selectedName == null || this.selectedName == "" || this.selectedName.trim().length == 0)
    { 
      swal({
        type: 'error',
        title:'you have to put a name first!',
        })
      }
      else
    {
    var self = this;
    this._data.getCompanyByName(this.selectedName).subscribe(
      function(company)
      {
        self.company = company;
        self.selectedId = company.id;
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

  public updateCompany()
  {
    this._data.updateCompany(this.company);
  }
}

