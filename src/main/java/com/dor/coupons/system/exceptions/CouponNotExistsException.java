package com.dor.coupons.system.exceptions;
/**
 * <p>Coupon not exists excption is for cases of wrong searches by users.</p>
 * <p>Used in getCoupon (by id) and getCouponByTitle methods at CompanyFacade class.</p>
 * <p>This is a Runtime exception.</p>
 * @author Dor aharon
 * @version 1.0
 * @category EXCEPTION class
 */
public class CouponNotExistsException extends RuntimeException{
	/**
	 * Default constractor for CouponNotExistsException.
	 * @param message Message you want to show when the exception happened.
	 */
	public CouponNotExistsException (String message)
	{
		super (message);
	}
}
