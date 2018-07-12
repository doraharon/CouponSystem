package com.dor.coupons.system.exceptions;
/**
 * <p>Customer exists exception is to prevent admin users to add a customer object twice to the database.</p>
 * <p>Used in createCustomer method at AdminFacade class.</p>
 * <p>This is a Runtime exception.</p>
 * @author Dor aharon
 * @version 1.0
 * @category EXCEPTION class
 */
public class CustomerExistsException extends RuntimeException{
	/**
	 * Default constractor for CustomerExistsException.
	 * @param message Message you want to show when the exception happened.
	 */
	public CustomerExistsException (String message)
	{
		super(message);
	}
}
