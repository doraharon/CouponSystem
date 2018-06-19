package com.dor.coupons.system.entities;
/**
 * <p>This ExceptionsMessages has all the Strings that used for exceptions messages in the system.</p> 
 * @author Dor aharon
 * @version 1.0
 * @category ENTITY class
 */
public class ExceptionsMessages {
	/**
	 *<p> This wrongUserOrPasswordExceptionMessage is the exception message when wrongUserOrPasswordException is thrown.</p>
	 *<p> Used in login method at AdminFacade, CompanyFacade and CustomerFacade classes.</p>
	 */
	public static final String wrongUserOrPasswordExceptionMessage = "Your user name or password are wrong!";
	/**
	 *<p> This companyExistsExceptionMessage is the exception message when companyExistsException is thrown.</p>
	 *<p>Used in createCompany method at AdminFacade class.</p>
	 */
	public static final String companyExistsExceptionMessage = "The company with the name: " + "%s" + " is aleady in the database!%n";
	/**
	 *<p> This companyNotExistsByIdExceptionMessage is the exception message when companyNotExistsByIdException is thrown.</p>
	 *<p>Used in getCompany (by id) method at AdminFacade class.</p>
	 */
	public static final String companyNotExistsByIdExceptionMessage = "The company with the id: " + "%d" + " not exists in the database!%n";
	/**
	 *<p> This companyNotExistsByNameExceptionMessage is the exception message when companyNotExistsByNameException is thrown.</p>
	 *<p>Used in getCompanyByName method at AdminFacade class.</p> 
	 */
	public static final String companyNotExistsByNameExceptionMessage = "The company with the name: " + "%s" + " not exists in the database!%n";
	/**
	 *<p> This customerExistsExceptionMessage is the exception message when customerExistsException is thrown.</p>
	 *<p>Used in createCustomer method at AdminFacade class.</p> 
	 */
	public static final String customerExistsExceptionMessage = "The customer with the name: " + "%s" + " is aleady in the database!%n";
	/**
	 *<p> This customerNotExistsByIdExceptionMessage is the exception message when customerNotExistsByIdException is thrown.</p>
	 *<p> Used in getCustomer (by id) method at AdminFacade class.</p> 
	 */
	public static final String customerNotExistsByIdExceptionMessage = "The customer with the id: " + "%d" + " not exists in the database!%n";
	/**
	 *<p> This customerNotExistsByNameExceptionMessage is the exception message when customerNotExistsByNameException is thrown.</p>
	 *<p> Used in getCustomerByName method at AdminFacade class.</p> 
	 */
	public static final String customerNotExistsByNameExceptionMessage = "The customer with the name: " + "%s" + " not exists in the database!%n";
	/**
	 *<p> This systemClosedExceptionMessage is the exception message when systemClosedException is thrown.</p>
	 *<p> Used in getSynchronize method at synchronizePool class.</p> 
	 */
	public static final String systemClosedExceptionMessage = "The system shutting down! Sorry and have a nice day!%n";
	/**
	 *<p> This couponExistsExceptionMessage is the exception message when couponExistsException is thrown.</p>
	 *<p> Used in createCoupon method at CompanyFacade class.</p> 
	 */
	public static final String couponExistsExceptionMessage = "The coupon with the title: " + "%s" + " is aleady in the database!%n";
	/**
	 *<p> This couponNotExistsByIdExceptionMessage is the exception message when couponNotExistsException search with an id is thrown..</p>
	 *<p> Used in getCoupon (by id) method at CompanyFacade class.</p> 
	 */
	public static final String couponNotExistsByIdExceptionMessage = "The coupon with the id: " + "%d" + " not exists in the database!%n";
	/**
	 *<p> This couponNotExistsByTitleExceptionMessage is the exception message when couponNotExistsByTitleException is thrown.</p>
	 *<p> Used in getCouponByTitle method at CompanyFacade class.</p> 
	 */
	public static final String couponNotExistsByTitleExceptionMessage = "The coupon with the title: " + "%s" + " not exists in the database!%n";
	/**
	 *<p> This couponAlreadyPurchasedExceptionMessage is the exception message when couponAlreadyPurchasedException is thrown.</p>
	 *<p> Used in purchaseCoupon method at CustomerFacade class.</p> 
	 */
	public static final String couponAlreadyPurchasedExceptionMessage = "The coupon: " + "%s" + " is aleady has been purchased!%n";
	/**
	 *<p> This couponExpierdExceptionMessage is the exception message when couponExpierdException is thrown.</p>
	 *<p> Used in purchaseCoupon method at CustomerFacade class.</p> 
	 */
	public static final String couponExpierdExceptionMessage = "The coupon: " + "%s" + " is expired!%n";
	/**
	 *<p> This couponOutOfStockExceptionMessage is the exception message when couponOutOfStockException is thrown.</p>
	 *<p> Used in purchaseCoupon method at CustomerFacade class.</p> 
	 */
	public static final String couponOutOfStockExceptionMessage = "The coupon: " + "%s" + " is out of stock!%n";
} 
