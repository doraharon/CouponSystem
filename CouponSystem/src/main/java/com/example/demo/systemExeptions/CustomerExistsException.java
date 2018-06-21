package com.example.demo.systemExeptions;

public class CustomerExistsException extends RuntimeException{
	public CustomerExistsException (String message)
	{
		super(message);
	}
}
