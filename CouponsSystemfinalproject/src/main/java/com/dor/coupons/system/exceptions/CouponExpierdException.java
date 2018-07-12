package com.dor.coupons.system.exceptions;
/**
 * <p>Coupon expired exception is to prevent customer users to purchase an expired coupon.</p>
 * <p>Used in purchaseCoupon method at CustomerFacade class.</p>
 * <p>This is a Runtime exception.</p>
 * @author Dor aharon
 * @version 1.0
 * @category EXCEPTION class
 */
public class CouponExpierdException extends RuntimeException{
	/**
	 * Default constractor for CouponExpierdException.
	 * @param message Message you want to show when the exception happened.
	 */
	public CouponExpierdException (String message)
	{
		super (message);
	}

}
