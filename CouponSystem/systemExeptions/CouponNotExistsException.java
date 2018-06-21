package com.example.demo.systemExeptions;

public class CouponNotExistsException extends RuntimeException {
	public CouponNotExistsException (String message)
	{
		super (message);
	}

}
