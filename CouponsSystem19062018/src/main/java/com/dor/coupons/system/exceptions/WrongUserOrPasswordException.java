package com.dor.coupons.system.exceptions;
/**
 * <p>Worng user or password excption is for cases of wrong login details by users.</p>
 * <p>Used in login method at AdminFacade, CompanyFacade and CustomerFacade classes.</p>
 * <p>This is a Runtime exception.</p>
 * @author Dor aharon
 * @version 1.0
 * @category EXCEPTION class
 */
public class WrongUserOrPasswordException extends RuntimeException {
	/**
	 * Default constractor for WrongUserOrPasswordException.
	 * @param message Message you want to show when the exception happened.
	 */
	public WrongUserOrPasswordException(String message)
	{
		super(message);
	}

}
