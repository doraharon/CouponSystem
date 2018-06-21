package com.example.demo.facades;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.dbdao.CompanyDBDAO;
import com.example.demo.dbdao.CouponDBDAO;
import com.example.demo.dbdao.CustomerDBDAO;
import com.example.demo.dbdao.TransactionDBDAO;
import com.example.demo.system.ClientType;
import com.example.demo.systemExeptions.CompanyExistsException;
import com.example.demo.systemExeptions.CompanyNotExistException;
import com.example.demo.systemExeptions.CustomerExistsException;
import com.example.demo.systemExeptions.CustomerNotExistException;
import com.example.demo.systemExeptions.WrongUserOrPasswordException;
import com.example.demo.tables.ActionType;
import com.example.demo.tables.Company;
import com.example.demo.tables.ComponentType;
import com.example.demo.tables.Customer;
import com.example.demo.tables.Transaction;

@Component
@Scope ("singleton")

public class AdminFacade implements CouponClientFacade{
	@Autowired
	CompanyDBDAO companyDBDAO;
	@Autowired
	CustomerDBDAO customerDBDAO;
	@Autowired
	CouponDBDAO couponDBDAO;
	@Autowired
	TransactionDBDAO transactionDBDAO;
	
	public AdminFacade ()
	{
		super();
	}
	
	public void createCompany (Company company) throws CompanyExistsException
	{
		Transaction transaction = new Transaction(ClientType.ADMIN,"admin",ActionType.CREATE,ComponentType.COMPANY,company.myToString(),false,"CompanyExistsException");
		if(companyDBDAO.checkCompanyExistsByName(company.getName()))
		{
			transactionDBDAO.createTransaction(transaction);
			throw new CompanyExistsException("The company is aleady in the database!");
		}
		companyDBDAO.createCompany(company);
		transaction.setSuccess(true);
		transaction.setException("");
		transactionDBDAO.createTransaction(transaction);
		System.out.println("The company was added to the database successfuly");
	
	}
	public void removeCompany (Company company)
	{
		Transaction transaction = new Transaction(ClientType.ADMIN,"admin",ActionType.REMOVE,ComponentType.COMPANY,company.myToString(),true,"");
		if (companyDBDAO.checkCompanyExistsByName(company.getName()))
		{
			companyDBDAO.removeCompany(company);
			transactionDBDAO.createTransaction(transaction);
			System.out.println("Company had been removed!");
		}
		else
		{
		transaction.setSuccess(false);
		transaction.setException("CompanyNotExistException");
		transactionDBDAO.createTransaction(transaction);
		throw new CompanyNotExistException("The company is not in the Database");
		}
	}
	
	public void updateCompany (Company company) throws CompanyNotExistException
	{
		Transaction transaction = new Transaction(ClientType.ADMIN,"admin",ActionType.UPDATE,ComponentType.COMPANY,company.myToString(),true,"");
		if (companyDBDAO.checkCompanyExistsByName(company.getName()))
		{
			transactionDBDAO.createTransaction(transaction);
			companyDBDAO.updateCompany(company);
		}
		else
		{
			transaction.setSuccess(false);
			transaction.setException("CompanyNotExistException");
			transactionDBDAO.createTransaction(transaction);
			throw new CompanyNotExistException("The company is not in the database!");
		}
	}
	
	public Company getCompany (long id)
	{
		Company company = companyDBDAO.getCompany(id);
		if (company == null)
		{
			throw new CompanyNotExistException("The company is not in the database!");
		}
		return company;
	}
	
	public Collection<Company> getAllCompanies ()
	{
		return companyDBDAO.getAllCompany();
			
	}
	
	public void createCustomer (Customer customer)
	{
		if(customerDBDAO.checkCustomerExistsByName(customer.getName()))
		{
			throw new CustomerExistsException("The customer is already in the database!");
		}
			customerDBDAO.createCustomer(customer);
			System.out.println("Your customer was added to the database successfuly");
	}
	public void removeCustomer (Customer customer)
	{		
		Transaction transaction = new Transaction(ClientType.ADMIN,"admin",ActionType.REMOVE,ComponentType.CUSTOMER,customer.myToString(),true, "");
		if (customerDBDAO.checkCustomerExistsByName(customer.getName()))
		{
			customerDBDAO.removeCustomer(customer);	
			transactionDBDAO.createTransaction(transaction);
			System.out.println("Customer has been removed");
		}
		else
		{
		transaction.setSuccess(false);
		transaction.setException("CustomerNotExistException");
		transactionDBDAO.createTransaction(transaction);
		throw new CustomerNotExistException("The customer dont exists in the Database");
		}
	}	
	
	public void updateCustomer	(Customer customer)
	{
		Transaction transaction = new Transaction(ClientType.ADMIN,"admin",ActionType.UPDATE,ComponentType.CUSTOMER,customer.myToString(),true,"");
		if (customerDBDAO.checkCustomerExistsByName(customer.getName()))
		{
			transactionDBDAO.createTransaction(transaction);
			customerDBDAO.updateCustomer(customer);
		}
		else
		{
			transaction.setSuccess(false);
			transaction.setException("CustomerNotExistException");
			transactionDBDAO.createTransaction(transaction);
			throw new CustomerNotExistException("The customer is not in the database!");
		}
	}
	
	public Customer getCustomer (long id)
	{
		Customer customer = customerDBDAO.getCustomer(id);
		if (customer == null)
		{
			throw new CustomerNotExistException("The customer is not in the database!");
		}
		return customer;
	}
	public Collection<Customer> getAllCustomer ()
	{
		return customerDBDAO.getAllCustomer();
	}
	
	@Override
	public CouponClientFacade login(String name, String password) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction(ClientType.ADMIN,name,ActionType.LOGIN,ComponentType.ADMIN,name,true,"");
		if (name == "admin" && password == "1234")
		{
			transactionDBDAO.createTransaction(transaction);
			System.out.println("You are loged in as admin!");
			return this;
		}
		transaction.setSuccess(false);
		transaction.setException("wrongUserOrPasswordException");
		transactionDBDAO.createTransaction(transaction);
		throw new WrongUserOrPasswordException("Your user name or password are wrong!");
	}
	
}
