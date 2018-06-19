package com.dor.coupons.system.exceptions;
/**
 * <p>Coupon exists exception is to prevent company users to add a coupon object twice to the database.</p>
 * <p>Used in createCoupon method at CompanyFacade class.</p>
 * <p>This is a Runtime exception.</p>
 * @author Dor aharon
 * @version 1.0
 * @category EXCEPTION class
 */
public class CouponExistsException extends RuntimeException{
	/**
	 * Default constractor for CouponExistsException.
	 * @param message Message you want to show when the exception happened.
	 */
	public CouponExistsException (String message) {
		super (message);
	}
	

}
