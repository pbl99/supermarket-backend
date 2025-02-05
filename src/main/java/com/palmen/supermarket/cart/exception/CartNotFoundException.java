package com.palmen.supermarket.cart.exception;

public class CartNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CartNotFoundException(String message) {
		super(message);
	}

}
