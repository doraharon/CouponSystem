import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Company } from '../common/Company';
import swal from 'sweetalert2';
import { DataService } from '../../services/data.service';
@Component({
  selector: 'app-remove-company',
  templateUrl: './remove-company.component.html',
  styleUrls: ['./remove-company.component.css']
})
export class RemoveCompanyComponent implements OnInit {

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
      }
    )
  }
  }

  public removeCompany()
  {
    this._data.removeCompany(this.company , this.selectedId)
  }
}

  