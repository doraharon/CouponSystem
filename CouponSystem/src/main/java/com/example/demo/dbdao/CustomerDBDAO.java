package com.example.demo.dbdao;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.systemExeptions.CustomerExistsException;
import com.example.demo.tables.Coupon;
import com.example.demo.tables.CouponType;
import com.example.demo.tables.CouponRepo;
import com.example.demo.tables.Customer;
import com.example.demo.tables.CustomerRepo;
@Component
@Scope ("singleton")
public class CustomerDBDAO implements CustomerDAO {
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	CouponRepo couponRepo;
	private long ConnectedId;
	
	public CustomerDBDAO() {
		super();
	}

	public long getConnectedId() {
		return ConnectedId;
	}

	public void setConnectedId(long connectedId) {
		ConnectedId = connectedId;
	}

	@Override
	public void createCustomer(Customer customer) throws CustomerExistsException {
		// TODO Auto-generated method stub
		
		customerRepo.save(customer);
	}

	@Override
	public void removeCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepo.deleteCustomerByName(customer.getName());
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepo.save(customer);
	}

	@Override
	public Customer getCustomer(long id) {
		// TODO Auto-generated method stub
		return customerRepo.findOne(id);
	}
	public boolean checkCustomerExistsByName (String name)
	{
		return customerRepo.existsByName(name);
	}
	public Customer getCustomerByName (String name)
	{
		return customerRepo.findCustomerByName(name);
	}

	@Override
	public ArrayList<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return (ArrayList<Customer>) customerRepo.findAll();
		
	}
	public Customer getConnectedCustomer ()
	{
		return customerRepo.findOne(this.ConnectedId);
	}
	
	
	@Override
	public Collection<Coupon> getCoupons() {
		// TODO Auto-generated method stub
		Customer customer = this.getConnectedCustomer();
		return customer.getCoupons();
	}
	public ArrayList<Coupon> getCouponsByType (CouponType couponType)
	{
		return customerRepo.getCouponsByType(this.ConnectedId, couponType);
	}
	
	public ArrayList<Coupon> getCouponsByTopPrice (double price)
	{
		return customerRepo.getCouponsByTopPrice(this.ConnectedId, price);
	}

	@Override
	public boolean login(String customerName, String password) {
		// TODO Auto-generated method stub
		boolean connected = customerRepo.existsByNameAndPassword(customerName, password);
		if (connected)
		{
			this.ConnectedId = customerRepo.findCustomerByName(customerName).getId();
			return true;
		}
		return false;
		
	}

}
