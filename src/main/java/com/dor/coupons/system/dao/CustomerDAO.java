package com.dor.coupons.system.dao;

import java.util.Collection;

import com.dor.coupons.system.tables.Coupon;
import com.dor.coupons.system.tables.Customer;
/**
 * <p>This CustomerDAO interface is for reading and writing to the Customer table in the database.</p>
 * <p>The interfece has the following methods:</p>
 * @author Dor aharon
 * @version 1.0
 * @category DAO interface
 */
public interface CustomerDAO {
	/**
	 * Add a customer to the database.
	 * @param customer The customer object you want to add to the database.
	 */
	void createCustomer (Customer customer);
	/**
	 * Delete customer from the database. 
	 * @param customer The customer object you want to delete from the database.
	 */
	void removeCustomer (Customer customer);
	/**
	 * Update existing customer in the database.
	 * @param customer The updated customer object you want to replace in the database.
	 */
	void updateCustomer (Customer customer);
	/**
	 * Get a customer object according to a given id. 
	 * @param id The id of the customer you want to get from the database.
	 * @return customer object with the given id.
	 */
	Customer getCustomer (long id);
	/**
	 * Get all existing customers from the database.
	 * @return Iterable of all the customers objects from the database.
	 */
	Iterable<Customer> getAllCustomers ();
	/**
	 * Get all coupons the client purchased.
	 * @return Collection of all the coupons that the customer purchase.
	 */
	Collection <Coupon> getCoupons ();
	/**
	 * Checks if customer exists in database by name and compares given password to the one in the database
	 * @param customerName Given customer name String for login
	 * @param password Given customer password String for login
	 * @return Boolean of login success
	 */
	boolean login (String customerName , String password);
}
