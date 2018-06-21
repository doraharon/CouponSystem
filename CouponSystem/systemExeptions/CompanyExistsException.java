package com.example.demo.systemExeptions;

public class CompanyExistsException extends RuntimeException{
	public CompanyExistsException (String message)
	{
		super (message);
	}

}
