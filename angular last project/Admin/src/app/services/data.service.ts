import { Injectable } from '@angular/core';
import { Company } from '.././components/common/Company'; 
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import swal from 'sweetalert2';
import { Customer } from '../components/common/Customer';
@Injectable()
export class DataService {
  constructor(private _http : Http) { }

  public createCompany(company : Company)
  {
    if (company.name == null || company.password == null || company.name == "" || company.Password == "")
    {
      swal({
        type: 'error',
        title: 'details are missing!',
        text: 'You have to fill name and password!',
      })
    }
    else
    {
    this._http.post('http://localhost:8080//admin/company', company).subscribe(function(response)
    {
      console.log(response);
    swal(
      'Good job!',
      'The Company ' + company.name + ' has been added successfully!',
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
  public createCustomer(customer : Customer)
  {
    if (customer.name == null || customer.password == null || customer.name == "" || customer.password == "")
    {
      swal({
        type: 'error',
        title: 'details are missing!',
        text: 'You have to fill name and password!',
      })
    }
    else
    {
    this._http.post('http://localhost:8080//admin/customer', customer).subscribe(function(response)
    {
      console.log(response);
    swal(
      'Good job!',
      'The Customer ' + customer.name + ' has been added successfully!',
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
  public getCompanies ()
  {
    return this._http.get('http://localhost:8080//admin/companies').map (
      function(companiesResponse)
      {
        return companiesResponse.json();
      })
  }
  public getCustomers ()
  {
    return this._http.get('http://localhost:8080//admin/customers').map (
      function(customersResponse)
      {
        return customersResponse.json();
      })
  }
  public getCompanyById (id : number)
  {
    return this._http.get(`http://localhost:8080/admin/company/${id}`).map (
      function(companyResponse)
      {
        return companyResponse.json();
      })
  }
  public getCompanyByName (name : string)
  {
    return this._http.get(`http://localhost:8080/admin/company/name/${name}`).map (
      function(companyResponse)
      {
        return companyResponse.json();
      })
  }
  public getCustomerById (id : number)
  {
    return this._http.get(`http://localhost:8080/admin/customer/${id}`).map (
      function(customerResponse)
      {
        return customerResponse.json();
      })
  }
  public getCustomerByName (name : string)
  {
    return this._http.get(`http://localhost:8080/admin/customer/name/${name}`).map (
      function(customerResponse)
      {
        return customerResponse.json();
      })
  }
  public removeCompany(company : Company , id : number)
  {
    if (company.name == null || company.name == "")
    {
      swal({
        type: 'error',
        title: 'details are missing!',
        text: 'You have to find the company first',
       })
    }
    else
    {
      swal({
        title: 'Are you sure?',
        text: "The Company " + company.name + " will be delete for ever!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
      if (result.value) {
        
          this._http.delete(`http://localhost:8080/admin/company/remove/${id}`).subscribe(function(response)
    {
      console.log(response);
    })
      swal(
        'Deleted!',
        'The Company ' + company.name + ' has been deleted successfully!',
        'success'
        )
      }
    })
   }
  }
  

  public removeCustomer(customer : Customer , id : number)
  {
    if (customer.name == null || customer.name == "")
    {
      swal({
        type: 'error',
        title: 'details are missing!',
        text: 'You have to find the customer first',
      })
    }
    else
    {
    swal({
      title: 'Are you sure?',
      text: "The Customer " + customer.name + " will be delete for ever!",
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
        if (result.value) {
        this._http.delete(`http://localhost:8080/admin/customer/remove/${id}`).subscribe(function(response)
    {
      console.log(response);
    })
      swal(
        'Deleted!',
        'The Customer ' + customer.name + ' has been deleted successfully!',
        'success'
        )
       }
     })
   }
  }
  public updateCompany(company : Company)
  {
    if (company.name == null || company.name == "")
    {
      swal({
       type: 'error',
       title: 'details are missing!',
       text: 'You have to find the company first',
      })
    }
    else
    {
      swal({
       title: 'Are you sure?',
       text: "All previous information of " + company.name + " will be lost!",
       type: 'warning',
       showCancelButton: true,
       confirmButtonColor: '#3085d6',
       cancelButtonColor: '#d33',
       confirmButtonText: 'Yes, update it!'
        }).then((result) => {
           if (result.value) {
         console.log(company)
         this._http.put(`http://localhost:8080//admin/company`, company).subscribe(function(response)
        {
          console.log(response);
        })
         swal(
          'Updated!',
          'The Company ' + company.name + ' has been updated successfully!',
          'success'
        )}
     })
    } 
  }
  public updateCustomer(customer : Customer)
  {
    if (customer.name == null || customer.name == "")
  {
    swal({
      type: 'error',
      title: 'details are missing!',
      text: 'You have to find the customer first',
    })
  }
  else
  {
    swal({
      title: 'Are you sure?',
      text: "All previous information of " + customer.name + " will be lost!",
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, update it!'
    }).then((result) => {
  if (result.value) {
    console.log(customer)
    this._http.put(`http://localhost:8080//admin/customer`, customer).subscribe(function(response)
  {
    console.log(response);
  })
    swal(
      'Updated!',
      'The Customer ' + customer.name + ' has been updated successfully!',
      'success'
       )
     }})
    }
  }
}

