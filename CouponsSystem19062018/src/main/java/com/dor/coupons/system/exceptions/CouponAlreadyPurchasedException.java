package com.dor.coupons.system.exceptions;
/**
 * <p>Coupon already purchased exception is to prevent customer users to purchase a coupon twice.</p>
 * <p>Used in purchaseCoupon method at CustomerFacade class.</p>
 * <p>This is a Runtime exception.</p>
 * @author Dor aharon
 * @version 1.0
 * @category EXCEPTION class
 */
public class CouponAlreadyPurchasedException extends RuntimeException{
	/**
	 * Default constractor for CouponAlreadyPurchasedException.
	 * @param message Message you want to show when the exception happened.
	 */
	public CouponAlreadyPurchasedException (String message)
	{
		super (message);
	}
}
