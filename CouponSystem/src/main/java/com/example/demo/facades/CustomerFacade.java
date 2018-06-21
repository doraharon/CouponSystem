package com.example.demo.facades;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.dbdao.CouponDBDAO;
import com.example.demo.dbdao.CustomerDBDAO;
import com.example.demo.dbdao.TransactionDBDAO;
import com.example.demo.system.ClientType;
import com.example.demo.systemExeptions.CouponAlreadyBoughtException;
import com.example.demo.systemExeptions.CouponExpierdException;
import com.example.demo.systemExeptions.CouponOutOfLimit;
import com.example.demo.systemExeptions.WrongUserOrPasswordException;
import com.example.demo.tables.ActionType;
import com.example.demo.tables.ComponentType;
import com.example.demo.tables.Coupon;
import com.example.demo.tables.CouponType;
import com.example.demo.tables.Customer;
import com.example.demo.tables.Transaction;

@Scope ("singleton")	
@Component
public class CustomerFacade implements CouponClientFacade {
	@Autowired 
	CustomerDBDAO customerDBDAO;
	@Autowired
	CouponDBDAO couponDBDAO;
	@Autowired
	TransactionDBDAO transactionDBDAO;
	
	public CustomerFacade ()
	{
		super ();
	}
	public void purchaseCoupon (Coupon coupon)
	{
		Customer customer = customerDBDAO.getConnectedCustomer();
		Transaction transaction = new Transaction(ClientType.CUSTOMER,customer.myToString(),ActionType.PURCHASE,ComponentType.COUPON,coupon.toString(),false,"");
		if (couponDBDAO.checkIfBoughtCoupon(coupon.getTitle(), customer.getId()))
		{
			transaction.setException("CouponAlreadyBoughtException");
			transactionDBDAO.createTransaction(transaction);
			throw new CouponAlreadyBoughtException("You cant buy the same coupon!");
		}
		if (coupon.getEndDate().before(new Date ()))
		{
			transaction.setException("CouponExpierdException");
			transactionDBDAO.createTransaction(transaction);
			throw new CouponExpierdException("The Coupon is out of date!");
		}
		if(coupon.getAmount()==0)
		{
			transaction.setException("CouponOutOfLimit");
			transactionDBDAO.createTransaction(transaction);
			throw new CouponOutOfLimit("The coupon stock is finished!" );
		}
		Collection<Coupon> coupons = customer.getCoupons();
		Coupon couponFromDB = couponDBDAO.getCouponByTitle(coupon.getTitle());
		coupons.add(couponFromDB);
		couponFromDB.setAmount(couponFromDB.getAmount()-1);
		couponDBDAO.updateCoupon(couponFromDB);
		customerDBDAO.updateCustomer(customer);
		transaction.setSuccess(true);
		transaction.setException("");
		transactionDBDAO.createTransaction(transaction);
		System.out.println("The coupon has been purched");
	}
	public Collection<Coupon> getAllPurchasedCoupons ()
	{
		return customerDBDAO.getConnectedCustomer().getCoupons();
	}
	public Collection<Coupon> getAllPurchasedCouponsByType (CouponType couponType)
	{
		return customerDBDAO.getCouponsByType(couponType);
	}
	public Collection<Coupon> getAllPurchasedCouponsByPrice (double price)
	{
		return customerDBDAO.getCouponsByTopPrice(price);
	}
	@Override
	public CouponClientFacade login(String name, String password) {
		// TODO Auto-generated method stub
		boolean connected = customerDBDAO.login(name, password);
		Transaction transaction = new Transaction(ClientType.CUSTOMER,name,ActionType.LOGIN,ComponentType.CUSTOMER,name,true,"");
		if (connected)
			{
			transactionDBDAO.createTransaction(transaction);
			System.out.println("You are loged in!");
			return this;
			}
		transaction.setSuccess(false);
		transaction.setException("wrongUserOrPasswordException");
		transactionDBDAO.createTransaction(transaction);
		throw new WrongUserOrPasswordException("Your user name or password are wrong!");
	}
}

