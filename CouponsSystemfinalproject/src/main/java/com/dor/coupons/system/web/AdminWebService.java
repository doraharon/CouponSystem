package com.dor.coupons.system.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
	private Logger logger = LogManager.getLogger(AdminWebService.class);
	
	public AdminWebService() {
		super ();
	}
	@RequestMapping (value = "/admin/company/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCompany(HttpServletRequest request, @PathVariable ("id") long id)
	{
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		try
		{
			Company company = adminFacade.getCompany(id);
			logger.info("getCompany() worked successfully");
			return ResponseEntity.ok().body(company);
		}
		catch (CompanyNotExistsException e)
		{
			logger.error("getCompany() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/admin/company/name/{name}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCompanyByName(HttpServletRequest request, @PathVariable ("name") String name)
	{
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		try
		{
			Company company = adminFacade.getCompanyByName(name);
			logger.info("getCompanyByName() worked successfully");
			return ResponseEntity.ok().body(company);
		}
		catch (CompanyNotExistsException e)
		{
			logger.error("getCompanyByName() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/admin/company" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> createCompany(HttpServletRequest request ,@RequestBody Company company)
	{
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		try
		{
			adminFacade.createCompany(company);
			logger.info("createCompany() worked successfully");
			return ResponseEntity.ok().body("Company has been added");
		}
		catch (CompanyExistsException e)
		{
			logger.error("createCompany() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/admin/company" , method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateCompany(HttpServletRequest request ,@RequestBody Company company)
	{
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		adminFacade.updateCompany(company);
		logger.info("updateCompany() worked successfully");
		return "Company has been updated";
	}
	@RequestMapping (value = "/admin/company/remove/{id}" , method = RequestMethod.DELETE)
	public String removeCompany(HttpServletRequest request, @PathVariable ("id") long id)
	{
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		Company company = adminFacade.getCompany(id);
		adminFacade.removeCompany(company);
		logger.info("removeCompany() worked successfully");
		return "Company has been removed";
	}
	@RequestMapping (value = "/admin/companies" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Company> getAllCompanies (HttpServletRequest request)
	{
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		logger.info("getAllCompanies() worked successfully");
		return adminFacade.getAllCompanies();
	}
	@RequestMapping (value = "/admin/customer/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getCustomer(HttpServletRequest request, @PathVariable ("id") long id)
	{
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		try
		{
			Customer customer = adminFacade.getCustomer(id);
			logger.info("getCustomer() worked successfully");
			return ResponseEntity.ok().body (customer);
		}
		catch (CustomerNotExistException e)
		{
			logger.error("getCustomer() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/admin/customer/name/{name}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCustomerByName(HttpServletRequest request, @PathVariable ("name") String name)
	{
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		try
		{
			Customer customer = adminFacade.getCustomerByName(name);
			logger.info("getCustomerByName() worked successfully");
			return ResponseEntity.ok().body (customer);
		}
		catch (CustomerNotExistException e)
		{
			logger.error("getCustomerByName() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/admin/customer" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ ResponseBody ResponseEntity<String> createCustomer(HttpServletRequest request, @RequestBody Customer customer)
	{
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		try
		{
			adminFacade.createCustomer(customer);
			logger.info("createCustomer() worked successfully");
			return ResponseEntity.ok().body("Customer has been added");
		}
		catch (CustomerExistsException e)
		{
			logger.error("createCustomer() failed! " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	@RequestMapping (value = "/admin/customer" , method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateCustomer(HttpServletRequest request, @RequestBody Customer customer)
	{
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		adminFacade.updateCustomer(customer);
		logger.info("updateCustomer() worked successfully");
		return "Customer has been updated";
	}
	@RequestMapping (value = "/admin/customer/remove/{id}" , method = RequestMethod.DELETE)
	public String removeCustomer(HttpServletRequest request, @PathVariable ("id") long id)
	{
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		Customer customer = adminFacade.getCustomer(id);
		adminFacade.removeCustomer(customer);
		logger.info("removeCustomer() worked successfully");
		return "Customer has been removed";
	}
	@RequestMapping (value = "/admin/customers" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Customer> getAllCustomers (HttpServletRequest request)
	{
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		logger.info("getAllCustomers() worked successfully");
		return adminFacade.getAllCustomers();
	}
}