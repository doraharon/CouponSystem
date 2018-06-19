package com.dor.coupons.system.entities;
/**
 * <p>This SystemVariables has all the Strings and numbers Variables that used in the system classes.</p>
 * <p>Except of exceptions messages and tables classes.</p> 
 * @author Dor aharon
 * @version 1.0
 * @category ENTITY class
 */
public class SystemVariables {
	/**
	 *<p> This zero used as 0.</p>
	 *<p> Used in shutdown method at the CouponSystem class, in the constractor at the SynchronizePool class, in the purchaseCoupon method at the CustomerFacade class. </p>
	 */
	public static final int zero = 0;
	/**
	 *<p> This one used as 1.</p>
	 *<p> Used in shutdown method at the CouponSystem class.</p>
	 */
	public static final int one = 1;
	/**
	 *<p> This day used as a 24 hours time.</p>
	 *<p> Used in run method at the ExpiredCouponThread class.</p>
	 */
	public static final long day = 1000*60*60*24;
	/**
	 *<p> This thirtySeconds used as a 30 seconds count down.</p>
	 *<p> Used in closeSynchronizePool method at the SynchronizePool class.</p>
	 */
	public static final long thirtySeconds = 1000*30;
	/**
	 *<p> This numberOfSychronizes its the number of synchronizes available in the system.</p>
	 *<p> Used in constractor at the SynchronizePool class.</p>
	 */
	public static final int numberOfSychronizes = 5;
	/**
	 *<p>This adminUserName is the user name of admin client type.</p>
	 *<p>Used login method in the AdminFacade.</p>
	 */
	public static final String adminUserName = "admin";
	/**
	 *<p>This adminPassword is the correct password to login as admin client type.</p>
	 *<p>Used login method in the AdminFacade.</p>
	 */
	public static final String adminPassword = "1234";
	/**
	 * <p>This shuttingDownNotification is a notification for the users that system is shutting down in 30 seconds.</p> 
	 * <p> Used in closeSynchronizePool method at the SynchronizePool class.</p>
	 */
	public static final String shuttingDownNotification = "System shutting down in 30 sec!";
	/**
	 *<p>This adminLoginSuccessfully the message when login as admin is successful.</p>
	 *<p>Used in login method in the AdminFacade.</p>
	 */
	public static final String adminLoginSuccessfully = "You are logged in as admin!";
	/**
	 *<p>This companyLoginSuccessfully is the message when login as company is successful.</p>
	 *<p>Used in login method in the CompanyFacade.</p>
	 */
	public static final String companyLoginSuccessfully = "You are logged in as company!";
	/**
	 *<p>This customerLoginSuccessfully is the message when login as customer is successful.</p>
	 *<p>Used in login method in the CustomerFacade.</p>
	 */
	public static final String customerLoginSuccessfully = "You are logged in as customer!";
	/**
	 *<p>This companyAddedSuccessfully is the message when adding a company to the database is successful.</p>
	 *<p>Used in createCompany method in the AdminFacade.</p>
	 */
	public static final String companyAddedSuccessfully = "The company: " + "%s" +  " was added successfully to the database! %n";
	/**
	 *<p>This companyRemovedSuccessfully is the message when removing a company from the database is successful.</p>
	 *<p>Used in removeCompany method in the AdminFacade.</p>
	 */
	public static final String companyRemovedSuccessfully = "The company: " + "%s" +  " was removed successfully from the database! %n";
	/**
	 *<p>This companyUpdatedSuccessfully is the message when updating a company to the database is successful.</p>
	 *<p>Used in updateCompany method in the AdminFacade.</p>
	 */
	public static final String companyUpdatedSuccessfully = "The company: " + "%s" +  " was updated successfully to the database! %n";
	/**
	 *<p>This customerAddedSuccessfully is the message when adding a customer to the database is successful.</p>
	 *<p>Used in createCustomer method in the AdminFacade.</p>
	 */
	public static final String customerAddedSuccessfully = "The customer: " + "%s" +  " was added successfully to the database! %n";
	/**
	 *<p>This customerRemovedSuccessfully is the message when removing a customer from the database is successful.</p>
	 *<p>Used in removeCustomer method in the AdminFacade.</p>
	 */
	public static final String customerRemovedSuccessfully = "The customer: " + "%s" +  " was removed successfully from the database! %n";
	/**
	 *<p>This customerUpdatedSuccessfully is the message when updating a customer to the database is successful.</p>
	 *<p>Used in updateCustomer method in the AdminFacade.</p>
	 */
	public static final String customerUpdatedSuccessfully = "The customer: " + "%s" +  " was updated successfully to the database! %n";
	/**
	 *<p>This couponAddedSuccessfully is the message when adding a coupon to the database is successful.</p>
	 *<p>Used in createCoupon method in the CompanyFacade.</p>
	 */
	public static final String couponAddedSuccessfully = "The coupon: " + "%s" +  " was added successfully to the database! %n";
	/**
	 *<p>This couponRemovedSuccessfully is the message when removing a coupon from the database is successful.</p>
	 *<p>Used in removeCoupon method in the CompanyFacade.</p>
	 */
	public static final String couponRemovedSuccessfully = "The coupon: " + "%s" +  " was removed successfully from the database! %n";
	/**
	 *<p>This couponUpdatedSuccessfully is the message when updating a coupon to the database is successful.</p>
	 *<p>Used in updateCoupon method in the CompanyFacade.</p>
	 */
	public static final String couponUpdatedSuccessfully = "The coupon: " + "%s" +  " was updated successfully to the database! %n";
	/**
	 *<p>This couponPurchasedSuccessfully is the message when purchase a coupon from the database is successful.</p>
	 *<p>Used in purchaseCoupon method in the CustomerFacade.</p>
	 */
	public static final String couponPurchasedSuccessfully = "The coupon: " + "%s" +  " was purchased successfully, thanks you for buying! %n";
	/**
	 *<p> This wrongUserOrPasswordException is the exception message in the transaction table when wrongUserOrPasswordException is thrown.</p>
	 *<p> Used in login method at AdminFacade, CompanyFacade and CustomerFacade classes.</p>
	 */
	public static final String wrongUserOrPasswordException = "WrongUserOrPasswordException";
	/**
	 *<p> This companyExistsException is the exception message in the transaction table when companyExistsException is thrown.</p>
	 *<p> Used in createCompany method at AdminFacade class.</p>
	 */
	public static final String companyExistsException = "CompanyExistsException";
	/**
	 *<p> This customerExistsException is the exception message in the transaction table when customerExistsException is thrown.</p>
	 *<p> Used in createCustomer method at AdminFacade class.</p>
	 */
	public static final String customerExistsException = "CustomerExistsException";
	/**
	 *<p> This couponExistsException is the exception message in the transaction table when couponExistsException is thrown.</p>
	 *<p> Used in createCoupon method at CompanyFacade class.</p>
	 */
	public static final String couponExistsException = "CouponExistsException";
	/**
	 *<p> This couponAlreadyPurchasedException is the exception message in the transaction table when couponAlreadyPurchasedException is thrown.</p>
	 *<p> Used in purchaseCoupon method at CustomerFacade class.</p>
	 */
	public static final String couponAlreadyPurchasedException = "CouponAlreadyPurchasedException";
	/**
	 *<p> This couponExpierdException is the exception message in the transaction table when couponExpierdException is thrown.</p>
	 *<p> Used in purchaseCoupon method at CustomerFacade class.</p>
	 */
	public static final String couponExpierdException = "CouponExpierdException";
	/**
	 *<p> This couponOutOfStockException is the exception message in the transaction table when couponOutOfStockException is thrown.</p>
	 *<p> Used in purchaseCoupon method at CustomerFacade class.</p>
	 */
	public static final String couponOutOfStockException = "CouponOutOfStockException";
	/**
	 *<p> This noException is an empty exception message in the transaction table when the action is successful.</p>
	 *<p> Used in all methods in AdminFacade, CompanyFacade and CustomerFacade classes, except get methods. </p>
	 */
	public static final String noException = "";

}
