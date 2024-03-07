package com.mastercard.resourceNotFound;

public class InsufficientFundException extends RuntimeException{

	public InsufficientFundException(String message) {
		super(message);
	}
}
