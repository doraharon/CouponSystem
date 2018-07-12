package com.dor.coupons.system.exceptions;
/**
 * <p>System closed excption is for cases of trying to get a synchronize from the synchronizePool in shutDown.</p>
 * <p>Used in getSynchronize method at synchronizePool class.</p>
 * <p>This is a Runtime exception.</p>
 * @author Dor aharon
 * @version 1.0
 * @category EXCEPTION class
 */
public class SystemClosedException extends RuntimeException {
	/**
	 * Default constractor for SystemClosedException.
	 * @param message Message you want to show when the exception happened.
	 */
	public SystemClosedException(String message) {
		super (message);
	}

}
