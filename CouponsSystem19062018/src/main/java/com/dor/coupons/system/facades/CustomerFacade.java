package com.dor.coupons.system.facades;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dor.coupons.system.core.ClientType;
import com.dor.coupons.system.dbdao.CouponDBDAO;
import com.dor.coupons.system.dbdao.CustomerDBDAO;
import com.dor.coupons.system.dbdao.TransactionDBDAO;
import com.dor.coupons.system.entities.ExceptionsMessages;
import com.dor.coupons.system.entities.SystemVariables;
import com.dor.coupons.system.exceptions.CouponAlreadyPurchasedException;
import com.dor.coupons.system.exceptions.CouponExistsException;
import com.dor.coupons.system.exceptions.CouponExpierdException;
import com.dor.coupons.system.exceptions.CouponNotExistsException;
import com.dor.coupons.system.exceptions.CouponOutOfStockException;
import com.dor.coupons.system.exceptions.WrongUserOrPasswordException;
import com.dor.coupons.system.tables.ActionType;
import com.dor.coupons.system.tables.ComponentType;
import com.dor.coupons.system.tables.Coupon;
import com.dor.coupons.system.tables.CouponType;
import com.dor.coupons.system.tables.Customer;
import com.dor.coupons.system.tables.Transaction;
/**
 * <p>This CustomerFacade is the functions Customer users will be able to use after successfull login to the system.</p>
 * <p>This CustomerFacade implements CouponClientFacade.</p>
 * <p>This CustomerFacade uses CustomerDBDAO, CouponDBDAO and TransactionDBDAO classes to read and write to the database.</p>
 * @author Dor aharon
 * @version 1.0
 * @category FACADE class
 */
@Scope ("singleton")	
@Component
public class CustomerFacade implements CouponClientFacade {
	@Autowired 
	CustomerDBDAO customerDBDAO;
	@Autowired
	CouponDBDAO couponDBDAO;
	@Autowired
	TransactionDBDAO transactionDBDAO;
	/**
	 *  Default constractor for CustomerFacade.
	 */
	public CustomerFacade ()
	{
		super ();
	}
	/**
	 *<p>This purchaseCoupon allow customer users to purchase coupon from the database by CouponDBDAO and customerDBDAO.</p>
	 *<p>The action will be saved at the transaction table if successed and if not by TransactionDBDAO.</p>
	 * @param coupon The coupon object that customer users want to purchase from the database.
	 * @throws CouponAlreadyPurchasedException When customer users will try to purchase a coupon that they already have. 
	 * @throws CouponExpierdException When customer users will try to purchase an expired coupon. (endDate has passed)
	 * @throws CouponOutOfStockException When customer users will try to purchase an out of stock coupon. (amount = 0)
	 */
	public void purchaseCoupon (Coupon coupon)
	{
		Customer customer = customerDBDAO.getConnectedCustomer();
		Transaction transaction = new Transaction(ClientType.CUSTOMER,customer.myToString(),ActionType.PURCHASE,ComponentType.COUPON,coupon.toString(),false,SystemVariables.noException);
		if (couponDBDAO.checkIfPurchasedCoupon(coupon.getTitle(), customer.getId()))
		{
			transaction.setException(SystemVariables.couponAlreadyPurchasedException);
			transactionDBDAO.createTransaction(transaction);
			String message = String.format(ExceptionsMessages.couponAlreadyPurchasedExceptionMessage, coupon.getTitle());
			throw new CouponAlreadyPurchasedException(message);
		}
		if (coupon.getEndDate().before(new Date ()))
		{
			transaction.setException(SystemVariables.couponExpierdException);
			transactionDBDAO.createTransaction(transaction);
			String message = String.format(ExceptionsMessages.couponExpierdExceptionMessage, coupon.getTitle());
			throw new CouponExpierdException(message);
		}
		if(coupon.getAmount()==SystemVariables.zero)
		{
			transaction.setException(SystemVariables.couponOutOfStockException);
			transactionDBDAO.createTransaction(transaction);
			String message = String.format(ExceptionsMessages.couponOutOfStockExceptionMessage, coupon.getTitle());
			throw new CouponOutOfStockException(message);
		}
		Collection<Coupon> coupons = customer.getCoupons();
		Coupon couponFromDB = couponDBDAO.getCouponByTitle(coupon.getTitle());
		coupons.add(couponFromDB);
		Integer couponAmount = couponFromDB.getAmount();
		couponAmount--;
		couponFromDB.setAmount(couponAmount);
		couponDBDAO.updateCoupon(couponFromDB);
		customerDBDAO.updateCustomer(customer);
		transaction.setSuccess(true);
		transaction.setException(SystemVariables.noException);
		transactionDBDAO.createTransaction(transaction);
		System.out.printf(SystemVariables.couponPurchasedSuccessfully, coupon);
	}
	/**
	 * <p>This getPurchasedCouponByTitle allow customer users to get their purchased coupon object by title from the database by CustomerDBDAO.</p>
	 * @param title The title of the coupon the customer users want to get from the database.
	 * @return Coupon object with the given title.
	 */
	@Cacheable (value = "getPurchasedCouponByTitleCache", key = "#title")
	public Coupon getPurchasedCouponByTitle(String title)
	{
		Coupon coupon = customerDBDAO.getPurchasedCoupoonByTitle(title);
		if (coupon == null)
		{
			String message = String.format(ExceptionsMessages.couponNotExistsByTitleExceptionMessage, title);
			throw new CouponNotExistsException(message);
		}
		return coupon;
	}
	@Cacheable (value = "getPurchasedCouponCache", key = "#id")
	public Coupon getPurchasedCoupon(long id)
	{
		Coupon coupon = customerDBDAO.getPurchasedCoupoonById(id);
		if (coupon == null)
		{
			String message = String.format(ExceptionsMessages.couponNotExistsByIdExceptionMessage, id);
			throw new CouponNotExistsException(message);
		}
		return coupon;
	}
	/**
	 * <p>This getAllPurchasedCoupons allow customer users to get all their purchased coupon object by title from the database by CustomerDBDAO.</p>
	 * @return Collection of coupons objects that the customer purchase.
	 */
	@Cacheable ("getAllPurchasedCouponsCache")
	public Collection<Coupon> getAllPurchasedCoupons ()
	{
		return customerDBDAO.getConnectedCustomer().getCoupons();
	}
	/**
	 * <p>This getPurchasedCouponsByType allow customer users to get their purchased coupons objects with a given coupon type from the database by CustomerDBDAO.</p>
	 * @param couponType The couponType of the coupons the customer users want to get from the database.
	 * @return Coupon object with the given title.
	 */
	@Cacheable ("getPurchasedCouponsByTypeCache")
	public Collection<Coupon> getPurchasedCouponsByType (CouponType couponType)
	{
		return customerDBDAO.getCouponsByType(couponType);
	}
	/**
	 * <p>This getPurchasedCouponsByPrice allow customer users to get all their purchased coupons whose price is lower or equel to a given price from the database by CompanyDBDAO.</p> 
	 * @param price The maximum price (of the coupon) the customer users want to get. 
	 * @return Collection of customer purchased coupons objects whose price is lower or equal to the given price. 
	 */
	@Cacheable ("getPurchasedCouponsByPriceCache")
	public Collection<Coupon> getPurchasedCouponsByPrice (double price)
	{
		return customerDBDAO.getCouponsByTopPrice(price);
	}
	@Cacheable ("getCouponsToBuyCache")
	public Collection<Coupon> getCouponsToBuy ()
	{
		return couponDBDAO.getCouponsToBuy(new Date());
	}
	@Cacheable (value = "customerGetCouponCache", key = "#id")
	public Coupon getCoupon (long id)
	{
		Coupon coupon = couponDBDAO.getCoupon(id);
		if (coupon == null)
		{
			String message = String.format(ExceptionsMessages.couponNotExistsByIdExceptionMessage, id);
			throw new CouponNotExistsException(message);
		}
		return coupon;
	}
	@Cacheable (value = "customerGetCouponByTitleCache", key = "#id")
	public Coupon getCouponByTitle (String title)
	{
		Coupon coupon = couponDBDAO.getCouponByTitle(title);
		if (coupon == null)
		{
			String message = String.format(ExceptionsMessages.couponNotExistsByTitleExceptionMessage, title);
			throw new CouponNotExistsException(message);
		}
		return coupon;
	}
	/**
	 *<p>{@inheritDoc}</p>
	 *<p>This login is for customer users only</p>
	 *<p>Customer name and password are send to the customerDBDAO login function to try and login to the system.</p>
	 *<p>The action will be saved at the transaction table if successed and if not by TransactionDBDAO.</p>
	 *@return CustomerFacade if login Succeed.
	 *@throws WrongUserOrPasswordException if login failed.
	 */
	@Override
	public CouponClientFacade login(String name, String password) {
		// TODO Auto-generated method stub
		boolean connected = customerDBDAO.login(name, password);
		Transaction transaction = new Transaction(ClientType.CUSTOMER,name,ActionType.LOGIN,ComponentType.CUSTOMER,name,true,SystemVariables.noException);
		if (connected)
		{
			transactionDBDAO.createTransaction(transaction);
			System.out.println(SystemVariables.customerLoginSuccessfully);
			return this;
		}
		transaction.setSuccess(false);
		transaction.setException(SystemVariables.wrongUserOrPasswordException);
		transactionDBDAO.createTransaction(transaction);
		throw new WrongUserOrPasswordException(SystemVariables.wrongUserOrPasswordException);
	}
}

