package com.example.demo.systemExeptions;

public class CouponAlreadyBoughtException extends RuntimeException
{
	public CouponAlreadyBoughtException (String message)
	{
		super (message);
	}
}
