package com.example.demo.systemExeptions;

public class CouponOutOfLimit extends RuntimeException{
	public CouponOutOfLimit (String message)
	{
		super (message);
	}
}
