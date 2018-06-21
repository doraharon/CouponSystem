package com.example.demo.dao;

import java.util.Collection;

import com.example.demo.tables.Coupon;
import com.example.demo.tables.Customer;

public interface CustomerDAO {
	void createCustomer (Customer customer);
	void removeCustomer (Customer customer);
	void updateCustomer (Customer customer);
	Customer getCustomer (long id);
	Collection <Customer> getAllCustomer ();
	Collection <Coupon> getCoupons ();
	boolean login (String customerName , String password);
}
