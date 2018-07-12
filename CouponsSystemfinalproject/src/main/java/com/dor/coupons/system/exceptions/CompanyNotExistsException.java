package com.dor.coupons.system.exceptions;
/**
 * <p>Company not exists excption is for cases of wrong searches by users.</p>
 * <p>Used in getCompany (by id) and getCompanyByName methods at AdminFacade class.</p>
 * <p>This is a Runtime exception.</p>
 * @author Dor aharon
 * @version 1.0
 * @category EXCEPTION class
 */
public class CompanyNotExistsException extends RuntimeException {
	/**
	 * Default constractor for CompanyNotExistsException.
	 * @param message Message you want to show when the exception happened.
	 */
	public CompanyNotExistsException(String message)
	{
		super (message);
	}
}
