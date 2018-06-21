package com.example.demo.systemExeptions;

public class CouponExistsException extends RuntimeException{
	public CouponExistsException (String message) {
		super (message);
	}
	

}
