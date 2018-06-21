package com.example.demo.systemExeptions;

public class WrongUserOrPasswordException extends RuntimeException {
	public WrongUserOrPasswordException(String message)
	{
		super(message);
	}

}
