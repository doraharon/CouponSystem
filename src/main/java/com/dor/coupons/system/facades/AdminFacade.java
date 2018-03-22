package com.dor.coupons.system.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dor.coupons.system.core.ClientType;
import com.dor.coupons.system.dbdao.CompanyDBDAO;
import com.dor.coupons.system.dbdao.CustomerDBDAO;
import com.dor.coupons.system.dbdao.TransactionDBDAO;
import com.dor.coupons.system.entities.ExceptionsMessages;
import com.dor.coupons.system.entities.SystemVariables;
import com.dor.coupons.system.entities.TestsVariables;
import com.dor.coupons.system.exceptions.CompanyExistsException;
import com.dor.coupons.system.exceptions.CompanyNotExistsException;
import com.dor.coupons.system.exceptions.CustomerExistsException;
import com.dor.coupons.system.exceptions.CustomerNotExistException;
import com.dor.coupons.system.exceptions.WrongUserOrPasswordException;
import com.dor.coupons.system.tables.ActionType;
import com.dor.coupons.system.tables.Company;
import com.dor.coupons.system.tables.ComponentType;
import com.dor.coupons.system.tables.Customer;
import com.dor.coupons.system.tables.Transaction;
/**
 * <p>This AdminFacade is the functions admin users will be able to use after successfull login to the system. </p>
 * <p>This AdminFacade implements CouponClientFacade.</p>
 * <p>This AdminFacade uses CompanyDBDAO, CustomerDBDAO and TransactionDBDAO classes to read and write to the database.</p>
 * @author Dor aharon
 * @version 1.0
 * @category FACADE class
 */
@Component
@Scope ("singleton")
public class AdminFacade implements CouponClientFacade{
	@Autowired
	CompanyDBDAO companyDBDAO;
	@Autowired
	CustomerDBDAO customerDBDAO;
	@Autowired
	TransactionDBDAO transactionDBDAO;
	/**
	 *  Default constractor for AdminFacade.
	 */
	public AdminFacade ()
	{
		super();
	}
	/**
	 *<p>This createCompany allow admin users to add company object to the database by CompanyDBDAO class.</p>
	 *<p>The action will be saved at the transaction table if successed and if not by TransactionDBDAO.</p>
	 * @param company The company object that admin users want to add to the database.
	 * @throws CompanyExistsException When admin users will try to add an existing company to the database. 
	 */
	public void createCompany (Company company) throws CompanyExistsException
	{
		Transaction transaction = new Transaction(ClientType.ADMIN, SystemVariables.adminUserName,ActionType.CREATE,ComponentType.COMPANY,company.myToString(),false,SystemVariables.companyExistsException);
		if(companyDBDAO.checkCompanyExistsByName(company.getName()))
		{
			transactionDBDAO.createTransaction(transaction);
			String message = String.format(ExceptionsMessages.companyExistsExceptionMessage, company.getName());
			throw new CompanyExistsException(message);
		}
		companyDBDAO.createCompany(company);
		transaction.setSuccess(true);
		transaction.setException(SystemVariables.noException);
		transactionDBDAO.createTransaction(transaction);
		System.out.printf(SystemVariables.companyAddedSuccessfully, company);
	}
	/**
	 * <p>This removeCompany is to allow admin users to delete company object from the database by CompanyDBDAO class.</p>
	 * <p>The action will be saved at the transaction table by TransactionDBDAO class.</p>
	 * @param company The company object that admin users want to delete from the database.
	 */
	public void removeCompany (Company company)
	{
		Transaction transaction = new Transaction(ClientType.ADMIN,SystemVariables.adminUserName,ActionType.REMOVE,ComponentType.COMPANY,company.myToString(),true,SystemVariables.noException);
		companyDBDAO.removeCompany(company);
		transactionDBDAO.createTransaction(transaction);
		System.out.printf(SystemVariables.companyRemovedSuccessfully, company);
	}
	/**
	 * <p>This updateCompany allow admin users to update company object to the database by CompanyDBDAO.</p>
	 * <p>Admin users can only update email and password details.</p>
	 * <p>The action will be saved at the transaction table if successed and if not by TransactionDBDAO.</p>
	 * @param company The updated company object that admin users want to update to the database.
	 */
	public void updateCompany (Company company) 
	{
		Transaction transaction = new Transaction(ClientType.ADMIN,SystemVariables.adminUserName,ActionType.UPDATE,ComponentType.COMPANY,company.myToString(),true,SystemVariables.noException);
		Company companyFromDB = companyDBDAO.getCompanyByName(company.getName());
		companyFromDB.setEmail(company.getEmail());
		companyFromDB.setPassword(company.getPassword());
		companyDBDAO.updateCompany(companyFromDB);
		transactionDBDAO.createTransaction(transaction);
		System.out.printf(SystemVariables.companyUpdatedSuccessfully,company);
	}
	/**
	 * <p>This getCompany allow admin users to get company object by id from the database by CompanyDBDAO.</p>
	 * @param id The id of the company you want to get from the database.
	 * @return Company object with the given id.
	 * @throws CompanyNotExistsException When admin users will give company id that not exists in the database.
	 */
	public Company getCompany (long id) throws CompanyNotExistsException
	{
		Company company = companyDBDAO.getCompany(id);
		if (company == null)
		{
			String message = String.format(ExceptionsMessages.companyNotExistsByIdExceptionMessage, id);
			throw new CompanyNotExistsException(message);
		}
		return company;
	}
	/**
	 * <p>This getCompanyByName allow admin users to get company object by name from the database by CompanyDBDAO.</p>
	 * @param name The name of the company you want to get from the database.
	 * @return Company object with the given name.
	 * @throws CompanyNotExistsException When admin users will give company name that not exists in the database.
	 */
	public Company getCompanyByName (String name) throws CompanyNotExistsException
	{
		Company company = companyDBDAO.getCompanyByName(name);
		if (company == null)
		{
			String message = String.format(ExceptionsMessages.companyNotExistsByNameExceptionMessage, name);
			throw new CompanyNotExistsException(message);
		}
		return company;
	}
	/**
	 * This getAllCompanies allow admin users to get all the companies in the database by CompanyDBDAO.</p>
	 * @return Iterable of all companies objects from the database.
	 */
	public Iterable<Company> getAllCompanies ()
	{
		return companyDBDAO.getAllCompanies();		
	}
	/**
	 *<p>This createCustomer allow admin users to add customer object to the database by CustomerDBDAO.</p>
	 *<p>If admin user will try to add customer that already exsists in the database the function will throw CustomerExistsException.</p>
	 *<p>The action will be saved at the transaction table if successed and if not by TransactionDBDAO.</p>
	 * @param customer The customer object that admin users want to add to the database.
	 * @throws CustomerExistsException When admin users will try to add an existing customer to the database. 
	 */
	public void createCustomer (Customer customer)
	{
		Transaction transaction = new Transaction(ClientType.ADMIN, SystemVariables.adminUserName,ActionType.CREATE,ComponentType.CUSTOMER,customer.myToString(),false,SystemVariables.customerExistsException);
		if(customerDBDAO.checkCustomerExistsByName(customer.getName()))
		{
			transactionDBDAO.createTransaction(transaction);
			String message = String.format(ExceptionsMessages.customerExistsExceptionMessage, customer.getName());
			throw new CustomerExistsException(message);
		}
		customerDBDAO.createCustomer(customer);
		transaction.setSuccess(true);
		transaction.setException(SystemVariables.noException);
		transactionDBDAO.createTransaction(transaction);
		System.out.printf(SystemVariables.customerAddedSuccessfully, customer);
	}
	/**
	 * <p>This removeCustomer allow admin users to delete customer object from the database by CustomerDBDAO.</p>
	 * <p>The action will be saved at the transaction table by TransactionDBDAO.</p>
	 * @param customer The customer object that admin users want to delete from the database.
	 */
	public void removeCustomer (Customer customer)
	{		
		Transaction transaction = new Transaction(ClientType.ADMIN,SystemVariables.adminUserName,ActionType.REMOVE,ComponentType.CUSTOMER,customer.myToString(),true, SystemVariables.noException);
		customerDBDAO.removeCustomer(customer);	
		transactionDBDAO.createTransaction(transaction);
		System.out.printf(SystemVariables.customerRemovedSuccessfully, customer);
	}	
	/**
	 * <p>This updateCustomer allow admin users to update customer object to the database by CustomerDBDAO.</p>
	 * <p>Admin users can only update password details.</p>
	 * <p>The action will be saved at the transaction table by TransactionDBDAO.</p>
	 * @param customer The updated customer object that admin users want to update to the database.
	 */
	public void updateCustomer	(Customer customer)
	{
		Transaction transaction = new Transaction(ClientType.ADMIN,SystemVariables.adminUserName,ActionType.UPDATE,ComponentType.CUSTOMER,customer.myToString(),true,SystemVariables.noException);
		Customer customerFromDB = customerDBDAO.getCustomerByName(customer.getName());
		customerFromDB.setPassword(customer.getPassword());
		customerDBDAO.updateCustomer(customerFromDB);
		transactionDBDAO.createTransaction(transaction);
		System.out.printf(SystemVariables.customerUpdatedSuccessfully, customer);
	}
	/**
	 * <p>This getCustomer allow admin users to get customer object by id from the database by CustomerDBDAO.</p>
	 * @param id The id of the customer you want to get from the database.
	 * @return Customer object with the given id.
	 * @throws CustomerNotExistException When admin users will give customer id that not exists in the database.
	 */
	public Customer getCustomer (long id)
	{
		Customer customer = customerDBDAO.getCustomer(id);
		if (customer == null)
		{
			String message = String.format(ExceptionsMessages.customerNotExistsByIdExceptionMessage, id);
			throw new CustomerNotExistException(message);
		}
		return customer;
	}
	/**
	 * <p>This getCustomerByName allow admin users to get customer object by name from the database by CustomerDBDAO.</p>
	 * @param name The name of the customer you want to get from the database.
	 * @return Customer object with the given name.
	 * @throws CustomerNotExistException When admin users will give customer name that not exists in the database.
	 */
	public Customer getCustomerByName (String name)
	{
		Customer customer = customerDBDAO.getCustomerByName(name);
		if (customer == null)
		{
			String message = String.format(ExceptionsMessages.customerNotExistsByNameExceptionMessage, name);
			throw new CustomerNotExistException(message);
		}
		return customer;
	}
	/**
	 * This getAllCustomers let admin users to get all the customers in the database by CustomerDBDAO.</p>
	 * @return Iterable of all customers objects from the database.
	 */
	public Iterable<Customer> getAllCustomers ()
	{
		return customerDBDAO.getAllCustomers();
	}
	/**
	 *<p>{@inheritDoc}</p>
	 *<p>This login is for admin users only</p>
	 *<p>successfull login will be only if the name is admin and password is 1234.</p>
	 *<p>The action will be saved at the transaction table if successed and if not by TransactionDBDAO.</p>
	 *@return AdminFacade if login Succeed.
	 *@throws WrongUserOrPasswordException if login failed.
	 */
	@Override
	public CouponClientFacade login(String name, String password) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction(ClientType.ADMIN,name,ActionType.LOGIN,ComponentType.ADMIN,name,true,SystemVariables.noException);
		if (name == SystemVariables.adminUserName && password == SystemVariables.adminPassword)
		{
			transactionDBDAO.createTransaction(transaction);
			System.out.println(SystemVariables.adminLoginSuccessfully);
			return this;
		}
		transaction.setSuccess(false);
		transaction.setException(SystemVariables.wrongUserOrPasswordException);
		transactionDBDAO.createTransaction(transaction);
		throw new WrongUserOrPasswordException(ExceptionsMessages.wrongUserOrPasswordExceptionMessage);
	}
}
