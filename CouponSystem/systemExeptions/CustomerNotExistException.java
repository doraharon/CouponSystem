package com.example.demo.systemExeptions;

public class CustomerNotExistException extends RuntimeException{
	public CustomerNotExistException (String message)
	{
		super (message);
	}

}
