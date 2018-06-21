package com.example.demo.facades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.dbdao.CompanyDBDAO;
import com.example.demo.dbdao.CouponDBDAO;
import com.example.demo.dbdao.TransactionDBDAO;
import com.example.demo.system.ClientType;
import com.example.demo.systemExeptions.CouponExistsException;
import com.example.demo.systemExeptions.CouponNotExistsException;
import com.example.demo.systemExeptions.WrongUserOrPasswordException;
import com.example.demo.tables.ActionType;
import com.example.demo.tables.Company;
import com.example.demo.tables.ComponentType;
import com.example.demo.tables.Coupon;
import com.example.demo.tables.CouponType;
import com.example.demo.tables.Transaction;

@Component
@Scope ("singleton")
public class CompanyFacade implements CouponClientFacade{
	@Autowired
	CompanyDBDAO companyDBDAO;
	@Autowired
	CouponDBDAO couponDBDAO;
	@Autowired
	TransactionDBDAO transactionDBDAO;
	public CompanyFacade ()
	{
		super ();
	} 
	
	public void createCoupon (Coupon coupon) 
	{
		Company company = companyDBDAO.getConnectedCompany();
		Transaction transaction = new Transaction(ClientType.COMPANY,company.myToString(),ActionType.CREATE,ComponentType.COUPON,coupon.toString(),false,"CouponExistsException");
		
		if (couponDBDAO.checkCouponExistsBytitle(coupon.getTitle()))
		{
			transactionDBDAO.createTransaction(transaction);
			throw new CouponExistsException("The coupon already in the database!");
		}
		couponDBDAO.createCoupon(coupon);
		Collection<Coupon> coupons = company.getCoupons();
		coupons.add(coupon);
		company.setCoupons(coupons);
		companyDBDAO.updateCompany(company);
		transaction.setSuccess(true);
		transaction.setException("");;
		transactionDBDAO.createTransaction(transaction);
		System.out.println("The coupon added succefully to the database");
	}
	public void removeCoupon (Coupon coupon)
	{
		Transaction transaction = new Transaction(ClientType.COMPANY,companyDBDAO.getConnectedCompany().myToString(),ActionType.REMOVE,ComponentType.COUPON,coupon.toString(),true,"");	
		if (couponDBDAO.checkCouponExistsBytitle(coupon.getTitle()))
		{
			couponDBDAO.removeCoupon(coupon);
			transactionDBDAO.createTransaction(transaction);
			System.out.println("The coupon has been removed!");
		}
		else
		{
			transaction.setSuccess(false);
			transaction.setException("CouponNotExistsException");
			transactionDBDAO.createTransaction(transaction);
		throw new CouponNotExistsException("The coupon is not in the data base!");
		}
	}
	public void updateCoupon (Coupon coupon)
	{
		Transaction transaction = new Transaction(ClientType.COMPANY,companyDBDAO.getConnectedCompany().myToString(),ActionType.UPDATE,ComponentType.COUPON,coupon.toString(),false,"CouponNotExistsException");
		Coupon couponFromDB = couponDBDAO.getCouponByTitle(coupon.getTitle());
		if (couponFromDB == null)
		{
			transactionDBDAO.createTransaction(transaction);
			throw new CouponNotExistsException("The coupon is not in the database!");
		}
		couponFromDB.setEndDate(coupon.getEndDate());
		couponFromDB.setPrice(coupon.getPrice());
		couponDBDAO.updateCoupon(couponFromDB);
		transaction.setSuccess(true);
		transaction.setException("");
		transactionDBDAO.createTransaction(transaction);
	}
	public Coupon getCoupon (long id)
	{
		Coupon coupon = couponDBDAO.getCoupon(id);
		if (coupon == null)
		{
			throw new CouponNotExistsException("The coupon is not in the database!");
		}
		return coupon;
	}
	public Collection <Coupon> getAllCoupon ()
	{
		return companyDBDAO.getCoupons();
	}
	public Collection <Coupon> getAllCouponByType (CouponType couponType)
	{
		return companyDBDAO.getCouponsByType(couponType);
	}
	
	public Collection<Coupon> getCouponByTopPrice(double price)  {
		
		return companyDBDAO.getCouponsByTopPrice(price);
	}
	public ArrayList<Coupon> getCouponByEndDate(Date endDate)  {
		
		return companyDBDAO.getCouponsByEndDate(endDate);
}

	@Override
	public CouponClientFacade login(String name, String password) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction(ClientType.COMPANY,name,ActionType.LOGIN,ComponentType.COMPANY,name,true,"");
		boolean connected = companyDBDAO.login(name, password);
		if (connected)
		{
			transactionDBDAO.createTransaction(transaction);
			System.out.println("you are loged in!");
			return this;
		}
		transaction.setSuccess(false);
		transaction.setException("wrongUserOrPasswordException");
		transactionDBDAO.createTransaction(transaction);
		throw new WrongUserOrPasswordException("Your user name or password are wrong!");
	}
}

