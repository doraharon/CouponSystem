package com.dor.coupons.system.entities;
/**
 * <p>This TestsVariables has all the Strings and numbers Variables that used in the system tests.</p> 
 * @author Dor aharon
 * @version 1.0
 * @category ENTITY class
 */
public class TestsVariables {
	/**
	 * This simpleDateFormat used at the main test class to declare the endDate format of coupon object.
	 */
	public static final String simpleDateFormat = "dd/MM/yyyy";
	/**
	 *<p>This adminUserName is the correct user name to login as admin client type.</p>
	 *<p>Used in all the tests.</p> 
	 */
	public static final String adminUserName = "admin";
	/**
	 *<p>This adminPassword is the correct password to login as admin client type.</p>
	 *<p>Used in tests numbers 01 , 03-40 .</p> 
	 */
	public static final String adminPassword = "1234";
	/**
	 *<p> This wrongPassword is the wrong client password to login to the system.</p>
	 *<p>Used in test02_adminLoginException.</p>
	 */
	public static final String wrongPassword = "oops i did it again! i put a wrong password";
	/**
	 *<p>This foxCompanyUserName is the correct user name of fox company.</p>
	 *<p>Used in tests 03-07 , 10-11 , 21-38 , 40.</p>  
	 */
	public static final String foxCompanyUserName = "Fox";
	/**
	 *<p>This foxCompanyPassword is the correct password of fox company.</p>
	 *<p>Used in tests 03-07 , 10-11 , 21-38 , 40.</p> 
	 */
	public static final String foxCompanyPassword = "shh";
	/**
	 *<p>This foxCompanyEmail is the email of fox company.</p>
	 *<p><p>Used in tests 03-07 , 10-11 , 21-38 , 40.</p>  
	 */
	public static final String foxCompanyEmail = "Fox@gmail.com";
	/**
	 *<p>This wrongUserName is the wrong client user name to login to the system or wrong name search in the database</p>
	 *<p>Used in tests numbers 06,09,18.</p> 
	 */
	public static final String wrongUserName = "who am i???";
	/**
	 *<p>This wrongIndex is the wrong way to look for an object in the database.</p>
	 *<p>Used in tests numbers 08,17,24.</p>
	 */
	public static final int wrongIndex = -1;
	/**
	 *<p>This hotCompanyUserName is the correct user name of hot company.</p>
	 *<p>Used in test10_getAllCompanies.</p> 
	 */
	public static final String hotCompanyUserName = "Hot";
	/**
	 *<p>This hotCompanyPassword is the correct password of hot company.</p>
	 *<p>Used in test10_getAllCompanies.</p> 
	 */
	public static final String hotCompanyPassword = "cold";
	/**
	 *<p>This hotCompanyEmail is the email of hot company.</p>
	 *<p>Used in test10_getAllCompanies.</p> 
	 */
	public static final String hotCompanyEmail = "Hot@walla.com";
	/**
	 *<p>This updatedPassword is the new updated password of a client.</p>
	 *<p>Used in tests numbers 11 , 20.</P>
	 */
	public static final String updatedPassword = "newPassword";
	/**
	 *<p>This updatedCompanyEmail is the new updated email of a company.</p>
	 *<p>Used in test11_updateCompany.</P>
	 */
	public static final String updatedCompanyEmail = "newMail@gmail.com";
	/**
	 *<p>This dorCustomerUserName is the correct user name of dor customer.</p>
	 *<p>Used in tests 12-16 , 19-20 , 27-30 , 35-37 , 39.</p> 
	 */
	public static final String dorCustomerUserName = "Dor";
	/**
	 *<p>This dorCustomerPassword is the correct password of dor customer.</p>
	 *<p>Used in tests 12-16 , 19-20 , 27-30 , 35-37 , 39.</p> 
	 */
	public static final String dorCustomerPassword = "king";
	/**
	 *<p>This cristinaCustomerUserName is the correct user name of cristina customer.</p>
	 *<p>Used in test19_getAllCustomers.</p> 
	 */
	public static final String cristinaCustomerUserName = "Cristina";
	/**
	 *<p>This cristinaCustomerPassword is the correct password of cristina customer.</p>
	 *<p>Used in test19_getAllCustomers.</p> 
	 */
	public static final String cristinaCustomerPassword = "queen";
	/**
	 *<p>This couponMessage is the the message of every coupon created in the tests .</p>
	 *<p>Used in tests numbers 21-23 , 26-38.</p> 
	 */
	public static final String couponMessage = "message";
	/**
	 *<p>This couponImage is the the message of every coupon created in the tests .</p>
	 *<p>Used in tests numbers 21-23 , 26-38.</p> 
	 */
	public static final String couponImage = "image";
	/**
	 *<p> This shoesCouponTitle is the title of the coupon shoes.</p>
	 *<p> Used in tests numbers 21-23 , 26-28 , 31-38.</p>
	 */
	public static final String shoesCouponTitle = "Shoes";
	/**
	 *<p> This shoesCouponEndDate is the expiration date of the coupon shoes.</p>
	 *<p> Used in tests numbers 21-23 , 26-28 , 31-38.</p>
	 */
	public static final String shoesCouponEndDate = "25/08/2018";
	/**
	 *<p> This shoesCouponAmount is the amount of the coupon shoes.</p>
	 *<p> Used in tests numbers 21-23 , 26-28 , 31-38.</p>
	 */
	public static final Integer shoesCouponAmount = 20;
	/**
	 *<p> This shoesCouponAmount is the price of the coupon shoes.</p>
	 *<p> Used in tests numbers 21-23 , 26-28 , 31-38.</p>
	 */
	public static final double shoesCouponPrice = 25;
	/**
	 *<p> This wrongCouponTitle is the wrong title of a coupon object.</p>
	 *<p> Used in test25_getCouponByTitleException.</p>
	 */
	public static final String wrongCouponTitle = "I am a wrong coupon title";
	/**
	 *<p> This updatedCouponEndDate is the updated expiration date of a coupon object.</p>
	 *<p> Used in test26_updateCoupon.</p>
	 */
	public static final String updatedCouponEndDate = "25/08/2019";
	/**
	 *<p> This updatedCouponPrice is the updated price of a coupon object.</p>
	 *<p> Used in test26_updateCoupon.</p>
	 */
	public static final double updatedCouponPrice = 100;
	/**
	 *<p> This bbbCouponTitle is the title of the coupon bbb.</p>
	 *<p> bbb coupon is an expired coupon.</p>
	 *<p> Used in tests numbers 29 , 31-34.</p>
	 */
	public static final String bbbCouponTitle = "bbb";
	/**
	 *<p> This bbbExpiredEndDate is the expiration date of the coupon bbb.</p>
	 *<p> bbb coupon is an expired coupon.</p>
	 *<p> Used in tests numbers 29 , 31-34.</p>
	 */
	public static final String bbbExpiredEndDate = "01/01/1995";
	/**
	 *<p> This bbbCouponAmount is the amount of the coupon bbb.</p>
	 *<p> bbb coupon is an expired coupon.</p>
	 *<p> Used in tests numbers 29 , 31-34.</p>
	 */
	public static final Integer bbbCouponAmount = 30;
	/**
	 *<p> This bbbCouponPrice is the price of the coupon bbb.</p>
	 *<p> bbb coupon is an expired coupon.</p>
	 *<p> Used in tests numbers 29 , 31-34.</p>
	 */
	public static final double bbbCouponPrice = 30;
	/**
	 *<p> This shirtsCouponTitle is the title of the coupon shirts.</p>
	 *<p> shirts coupon is an out of stock coupon.</p>
	 *<p> Used in tests numbers 30-34.</p>
	 */
	public static final String shirtsCouponTitle = "Shirts";
	/**
	 *<p> This shirtsCouponEndDate is the expiration date of the coupon shirts.</p>
	 *<p> shirts coupon is an out of stock coupon.</p>
	 *<p> Used in tests numbers 30-34.</p>
	 */
	public static final String shirtsCouponEndDate = "22/04/2019";
	/**
	 *<p> This shirtsOutOfStockAmount is the amount of the coupon shirts.</p>
	 *<p> shirts coupon is an out of stock coupon.</p>
	 *<p> Used in tests numbers 30-34.</p>
	 */
	public static final Integer shirtsOutOfStockAmount = 0;
	/**
	 *<p> This shirtsOutOfStockAmount is the price of the coupon shirts.</p>
	 *<p> shirts coupon is an out of stock coupon.</p>
	 *<p> Used in tests numbers 30-34.</p>
	 */
	public static final double shirtsCouponPrice = 20;
	/**
	 *<p> This tentCouponTitle is the title of the coupon tent.</p>
	 *<p> Used in tests numbers 35-37.</p>
	 */
	public static final String tentCouponTitle = "tent";
	/**
	 *<p> This tentCouponEndDate is the expiration date of the coupon tent.</p>
	 *<p> Used in tests numbers 35-37.</p>
	 */
	public static final String tentCouponEndDate = "30/12/2018";
	/**
	 *<p> This tentCouponAmount is the amount of the coupon tent.</p>
	 *<p> Used in tests numbers 35-37.</p>
	 */
	public static final Integer tentCouponAmount = 25;
	/**
	 *<p> This tentCouponPrice is the price of the coupon tent.</p>
	 *<p> Used in tests numbers 35-37.</p>
	 */
	public static final double tentCouponPrice = 120;
	/**
	 *<p> This pantsCouponTitle is the title of the coupon pants.</p>
	 *<p> Used in tests numbers 35-37.</p>
	 */
	public static final String pantsCouponTitle = "pants";
	/**
	 *<p> This pantsCouponEndDate is the expiration date of the coupon pants.</p>
	 *<p> Used in tests numbers 35-37.</p>
	 */
	public static final String pantsCouponEndDate = "2/4/2019";
	/**
	 *<p> This pantsCouponAmount is the amount of the coupon pants.</p>
	 *<p> Used in tests numbers 35-37.</p>
	 */
	public static final Integer pantsCouponAmount = 50;
	/**
	 *<p> This pantsCouponAmount is the price of the coupon pants.</p>
	 *<p> Used in tests numbers 35-37.</p>
	 */
	public static final double pantsCouponPrice = 40;	
}
