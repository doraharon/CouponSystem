package com.dor.coupons.system.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dor.coupons.system.exceptions.CompanyExistsException;
import com.dor.coupons.system.exceptions.CompanyNotExistsException;
import com.dor.coupons.system.exceptions.CustomerExistsException;
import com.dor.coupons.system.exceptions.CustomerNotExistException;
import com.dor.coupons.system.facades.AdminFacade;
import com.dor.coupons.system.tables.Company;
import com.dor.coupons.system.tables.Customer;

@RestController
@CrossOrigin("*")
public class AdminWebService {
	@Autowired
	AdminFacade adminFacade;
	public AdminWebService() {
		super ();
	}
	@RequestMapping (value = "/admin/company/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCompany(HttpServletRequest request, @PathVariable ("id") long id)
	{
		//AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		try
		{
			return ResponseEntity.ok().body (adminFacade.getCompany(id));
		}
		catch (CompanyNotExistsException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/admin/company/name/{name}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCompanyByName(HttpServletRequest request, @PathVariable ("name") String name)
	{
		//AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		try
		{
			return ResponseEntity.ok().body (adminFacade.getCompanyByName(name));
		}
		catch (CompanyNotExistsException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/admin/company" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> createCompany(HttpServletRequest request ,@RequestBody Company company)
	{
		//AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		try
		{
			adminFacade.createCompany(company);
			return ResponseEntity.ok().body("Company has been added");
		}
		catch (CompanyExistsException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/admin/company" , method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateCompany(HttpServletRequest request ,@RequestBody Company company)
	{
		//AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		adminFacade.updateCompany(company);
		return "Company has been updated";
	}
	@RequestMapping (value = "/admin/company/remove/{id}" , method = RequestMethod.DELETE)
	public String removeCompany(HttpServletRequest request, @PathVariable ("id") long id)
	{
		//AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		Company company = adminFacade.getCompany(id);
		adminFacade.removeCompany(company);
		return "Company has been removed";
	}
	@RequestMapping (value = "/admin/companies" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Company> getAllCompanies (HttpServletRequest request)
	{
		//AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		System.out.println("cach");
		return adminFacade.getAllCompanies();
	}
	
	@RequestMapping (value = "/admin/customer/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCustomer(HttpServletRequest request, @PathVariable ("id") long id)
	{
		//AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		try
		{
			return ResponseEntity.ok().body (adminFacade.getCustomer(id));
		}
		catch (CustomerNotExistException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/admin/customer/name/{name}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCustomerByName(HttpServletRequest request, @PathVariable ("name") String name)
	{
		//AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		try
		{
			return ResponseEntity.ok().body (adminFacade.getCustomerByName(name));
		}
		catch (CustomerNotExistException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/admin/customer" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ ResponseBody ResponseEntity<String> createCustomer(HttpServletRequest request, @RequestBody Customer customer)
	{
		//AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		try
		{
			adminFacade.createCustomer(customer);
			return ResponseEntity.ok().body("Customer has been added");
		}
		catch (CustomerExistsException e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/admin/customer" , method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateCustomer(HttpServletRequest request, @RequestBody Customer customer)
	{
		//AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		adminFacade.updateCustomer(customer);
		return "Customer has been updated";
	}
	@RequestMapping (value = "/admin/customer/remove/{id}" , method = RequestMethod.DELETE)
	public String removeCustomer(HttpServletRequest request, @PathVariable ("id") long id)
	{
		//AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		Customer customer = adminFacade.getCustomer(id);
		adminFacade.removeCustomer(customer);
		return "Customer has been removed";
	}
	@RequestMapping (value = "/admin/customers" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Customer> getAllCustomers (HttpServletRequest request)
	{
		//AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		return adminFacade.getAllCustomers();
	}
}