package com.example.demo.dao;

import java.util.Collection;

import com.example.demo.tables.Company;
import com.example.demo.tables.Coupon;

public interface CompanyDAO {
	void createCompany (Company company); 
	void removeCompany (Company company);
	void updateCompany (Company company);
	Company getCompany (long id);
	Collection<Company> getAllCompany();
	Collection<Coupon> getCoupons();
	boolean login (String companyName , String password);
}
