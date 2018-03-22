package com.dor.coupons.system.dao;

import java.util.Collection;

import com.dor.coupons.system.tables.Company;
import com.dor.coupons.system.tables.Coupon;
/**
 * <p>This CompanyDAO interface is for reading and writing to the Company table in the database.</p>
 * <p>The interfece has the following methods:</p>
 * @author Dor aharon
 * @version 1.0
 * @category DAO interface
 */
public interface CompanyDAO {
	/**
	 * Add a company to the database.
	 * @param company The company object you want to add to the database.
	 */
	void createCompany (Company company);
	/**
	 * Delete company from the database. 
	 * @param company The company object you want to delete from the database.
	 */
	void removeCompany (Company company);
	/**
	 * Update existing company object in the database.
	 * @param company The updated company object you want to replace in the database.
	 */
	void updateCompany (Company company);
	/**
	 * Get a company object according to a given id. 
	 * @param id The id of the company you want to get from the database.
	 * @return Company object according to a given id.
	 */
	Company getCompany (long id);
	/**
	 * Get all existing companies from the database
	 * @return Iterable of all the companies objects from the database.
	 */
	Iterable<Company> getAllCompanies();
	/**
	 * Get all existing coupons from the database
	 * @return Collection of all coupons objects from the database
	 */
	Collection<Coupon> getCoupons();
	/**
	 * Checks if company exists in database by name and compares given password to the one in the database.
	 * @param companyName Given company name String for login.
	 * @param password Given company password String for login.
	 * @return Boolean of login success.
	 */
	boolean login (String companyName , String password);
}
