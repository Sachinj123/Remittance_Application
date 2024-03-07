package com.mastercard.resourceNotFound;

public class InvalidAccountException extends RuntimeException{
	
	public InvalidAccountException(String message) {
		super(message);
	}

}
