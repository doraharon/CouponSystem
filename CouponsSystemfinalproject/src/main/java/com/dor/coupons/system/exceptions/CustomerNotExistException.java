package com.dor.coupons.system.exceptions;
/**
 * <p>Customer not exists excption is for cases of wrong searches by users.</p>
 * <p>Used in getCustomer (by id) and getCustomerByName methods at AdminFacade class.</p>
 * <p>This is a Runtime exception.</p>
 * @author Dor aharon
 * @version 1.0
 * @category EXCEPTION class
 */
public class CustomerNotExistException extends RuntimeException {
	/**
	 * Default constractor for CustomerNotExistException.
	 * @param message Message you want to show when the exception happened.
	 */
	public CustomerNotExistException (String message)
	{
		super (message);
	}
}
