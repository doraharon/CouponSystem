package com.dor.coupons.system.exceptions;


/**
 * <p>Company exists exception is to prevent admin users to add a company object twice to the database.</p>
 * <p>Used in createCompany method at AdminFacade class.</p>
 * <p>This is a Runtime exception.</p>
 * @author Dor aharon
 * @version 1.0
 * @category EXCEPTION class
 */
public class CompanyExistsException extends RuntimeException
{
	/**
	 * Default constractor for CompanyExistsException.
	 * @param message Message you want to show when the exception happened.
	 */
	public CompanyExistsException (String message)
	{
		super (message);
	}
}
