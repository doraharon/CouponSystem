package com.dor.coupons.system.exceptions;
/**
 * <p>Coupon out of stock exception is to prevent customer users to purchase a coupon that his amount is 0.</p>
 * <p>Used in purchaseCoupon method at CustomerFacade class.</p>
 * <p>This is a Runtime exception.</p>
 * @author Dor aharon
 * @version 1.0
 * @category EXCEPTION class
 */
public class CouponOutOfStockException extends RuntimeException{
	/**
	 * Default constractor for CouponOutOfStockException.
	 * @param message Message you want to show when the exception happened.
	 */
	public CouponOutOfStockException(String message) {
		// TODO Auto-generated constructor stub
		super (message);
	}
}
