package com.dor.coupons.system.facades;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dor.coupons.system.core.ClientType;
import com.dor.coupons.system.dbdao.CompanyDBDAO;
import com.dor.coupons.system.dbdao.CouponDBDAO;
import com.dor.coupons.system.dbdao.TransactionDBDAO;
import com.dor.coupons.system.entities.ExceptionsMessages;
import com.dor.coupons.system.entities.SystemVariables;
import com.dor.coupons.system.exceptions.CouponExistsException;
import com.dor.coupons.system.exceptions.CouponNotExistsException;
import com.dor.coupons.system.exceptions.WrongUserOrPasswordException;
import com.dor.coupons.system.tables.ActionType;
import com.dor.coupons.system.tables.Company;
import com.dor.coupons.system.tables.ComponentType;
import com.dor.coupons.system.tables.Coupon;
import com.dor.coupons.system.tables.CouponType;
import com.dor.coupons.system.tables.Transaction;
/**
 * <p>This CompanyFacade is the functions Company users will be able to use after successfull login to the system. </p>
 * <p>This CompanyFacade implements CouponClientFacade.</p>
 * <p>This AdminFacade uses CompanyDBDAO, CouponDBDAO and TransactionDBDAO classes to read and write to the database.</p>
 * @author Dor aharon
 * @version 1.0
 * @category FACADE class
 */
@Component
@Scope ("singleton")
public class CompanyFacade implements CouponClientFacade{
	@Autowired
	CompanyDBDAO companyDBDAO;
	@Autowired
	CouponDBDAO couponDBDAO;
	@Autowired
	TransactionDBDAO transactionDBDAO;
	/**
	 *  Default constractor for CompanyFacade.
	 */
	public CompanyFacade ()
	{
		super ();
	} 
	/**
	 *<p>This createCoupon allow company users to add coupon object to the database by CouponDBDAO.</p>
	 *<p>The action will be saved at the transaction table if successed and if not by TransactionDBDAO.</p>
	 * @param coupon The coupon object that company users want to add to the database.
	 * @throws CouponExistsException When company users will try to add an existing coupon to the database. 
	 */
	public void createCoupon (Coupon coupon) 
	{
		Company company = companyDBDAO.getConnectedCompany();
		Transaction transaction = new Transaction(ClientType.COMPANY,company.myToString(),ActionType.CREATE,ComponentType.COUPON,coupon.toString(),false,SystemVariables.couponExistsException);
		
		if (companyDBDAO.getCouponByTitle(coupon.getTitle()) != null)
		{
			transactionDBDAO.createTransaction(transaction);
			String message = String.format(ExceptionsMessages.couponExistsExceptionMessage, coupon.getTitle());
			throw new CouponExistsException(message);
		}
		couponDBDAO.createCoupon(coupon);
		Collection<Coupon> coupons = company.getCoupons();
		coupons.add(coupon);
		company.setCoupons(coupons);
		companyDBDAO.updateCompany(company);
		transaction.setSuccess(true);
		transaction.setException(SystemVariables.noException);;
		transactionDBDAO.createTransaction(transaction);
		System.out.printf(SystemVariables.couponAddedSuccessfully, coupon);
	}
	/**
	 * <p>This removeCoupon allow company users to delete coupon object from the database by CouponDBDAO.</p>
	 * <p>The action will be saved at the transaction table by TransactionDBDAO.</p>
	 * @param coupon The coupon object that company users want to delete from the database.
	 */
	public void removeCoupon (Coupon coupon)
	{
		Transaction transaction = new Transaction(ClientType.COMPANY,companyDBDAO.getConnectedCompany().myToString(),ActionType.REMOVE,ComponentType.COUPON,coupon.toString(),true,SystemVariables.noException);	
		couponDBDAO.removeCoupon(coupon);
		transactionDBDAO.createTransaction(transaction);
		System.out.printf(SystemVariables.couponRemovedSuccessfully, coupon);
	}
	/**
	 * <p>This updateCustomer allow company users to update coupon object to the database by CouponDBDAO. </p>
	 * <p>Company users can only update endDate and price details.</p>
	 * <p>The action will be saved at the transaction table by TransactionDBDAO.</p>
	 * @param coupon The updated coupon object that company users want to update to the database.
	 */
	public void updateCoupon (Coupon coupon)
	{
		Transaction transaction = new Transaction(ClientType.COMPANY,companyDBDAO.getConnectedCompany().myToString(),ActionType.UPDATE,ComponentType.COUPON,coupon.toString(),true,SystemVariables.noException);
		Coupon couponFromDB = couponDBDAO.getCouponByTitle(coupon.getTitle());
		couponFromDB.setEndDate(coupon.getEndDate());
		couponFromDB.setPrice(coupon.getPrice());
		couponDBDAO.updateCoupon(couponFromDB);
		transactionDBDAO.createTransaction(transaction);
		System.out.printf(SystemVariables.couponUpdatedSuccessfully, coupon);
	}
	/**
	 * <p>This getCoupon allow company users to get their coupon object by id from the database by CompanyDBDAO.</p>
	 * @param id The id of the coupon you want to get from the database.
	 * @return Coupon object with the given id.
	 * @throws CouponNotExistsException When company users will give coupon id that not exists in the database.
	 */
	@Cacheable (value = "getCouponCache", key = "#id")
	public Coupon getCoupon (long id)
	{
		Coupon coupon = companyDBDAO.getCouponById(id);
		if (coupon == null)
		{
			String message = String.format(ExceptionsMessages.couponNotExistsByIdExceptionMessage, id);
			throw new CouponNotExistsException(message);
		}
		return coupon;
	}
	/**
	 * <p>This getCoupon allow company users to get their coupon object by title from the database by CompanyDBDAO.</p>
	 * @param title The title of the coupon you want to get from the database.
	 * @return Coupon object with the given title.
	 * @throws CouponNotExistsException When company users will give coupon title that not exists in the database.
	 */
	@Cacheable (value = "getCouponByTitleCache", key = "#title")
	public Coupon getCouponByTitle (String title)
	{
		Coupon coupon = companyDBDAO.getCouponByTitle(title);
		if (coupon == null)
		{
			String message = String.format(ExceptionsMessages.couponNotExistsByTitleExceptionMessage, title);
			throw new CouponNotExistsException(message);
		}
		return coupon;
	}
	/**
	 *<p>This getAllCoupons allow company users to get all their coupons from the database by CompanyDBDAO.</p>
	 * @return Collection of all company's coupons objects from the database.
	 * @throws CouponNotExistsException when there no coupons in the system.
	 */
	@Cacheable ("getAllCouponsCache")
	public Collection <Coupon> getAllCoupons ()
	{
		return companyDBDAO.getCoupons();
	}
	/**
	 *<p>This getAllCouponsByType allow company users to get all their coupons with given coupon type from the database by CompanyDBDAO. </p> 
	 * @param couponType Enum of the coupon type you want to get fron the database.
	 * @return Collection of company's coupons objects with the given coupon type.
	 */
	@Cacheable ("getAllCouponsByTypeCache")
	public Collection <Coupon> getAllCouponsByType (CouponType couponType)
	{
		return companyDBDAO.getCouponsByType(couponType);
	}
	/**
	 * <p>This getAllCouponsByPrice allow company users to get all thier coupons whose price is lower or equel to a given price from the database by CompanyDBDAO.</p> 
	 * @param price The maximum price (of the coupon) you want to get. 
	 * @return Collection of coupons objects whose price is lower or equal to the given price. 
	 */
	@Cacheable ("getCouponsByTopPriceCache")
	public Collection<Coupon> getCouponsByTopPrice(double price)  {
		
		return companyDBDAO.getCouponsByTopPrice(price);
	}
	/**
	 * <p>This getAllCouponsByEndDate allow company users to get all thier coupons whose end date is before and same with given date from the database by CompanyDBDAO.</p>
	 * @param price The maximum price (of the coupon) you want to get. 
	 * @return Collection of coupons objects whose price is lower or equal to the given price. 
	 */
	@Cacheable ("getCouponsByEndDateCache")
	public Collection<Coupon> getCouponsByEndDate(Date endDate)  {
		
		return companyDBDAO.getCouponsByEndDate(endDate);
	}
	/**
	 *<p>{@inheritDoc}</p>
	 *<p>This login is for company users only</p>
	 *<p>Company name and password are send to the companyDBDAO login function to try and login to the system.</p>
	 *<p>The action will be saved at the transaction table if successed and if not by TransactionDBDAO.</p>
	 *@return CompanyFacade if login Succeed.
	 *@throws WrongUserOrPasswordException if login failed.
	 */
	@Override
	public CouponClientFacade login(String name, String password) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction(ClientType.COMPANY,name,ActionType.LOGIN,ComponentType.COMPANY,name,true,SystemVariables.noException);
		boolean connected = companyDBDAO.login(name, password);
		if (connected)
		{
			transactionDBDAO.createTransaction(transaction);
			System.out.println(SystemVariables.companyLoginSuccessfully);
			return this;
		}
		transaction.setSuccess(false);
		transaction.setException(SystemVariables.wrongUserOrPasswordException);
		transactionDBDAO.createTransaction(transaction);
		throw new WrongUserOrPasswordException(ExceptionsMessages.wrongUserOrPasswordExceptionMessage);
	}
}

