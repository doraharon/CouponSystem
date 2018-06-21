package com.example.demo.systemExeptions;

public class CompanyNotExistException extends RuntimeException{
	public CompanyNotExistException (String message)
	{
		super (message);
	}

}
