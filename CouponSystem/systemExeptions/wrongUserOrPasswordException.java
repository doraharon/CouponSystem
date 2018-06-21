package com.example.demo.systemExeptions;

public class wrongUserOrPasswordException extends RuntimeException {
	public wrongUserOrPasswordException(String message)
	{
		super(message);
	}

}
