package com.palmen.supermarket.user.exception;

public class WishlistNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WishlistNotFoundException(String message) {
		super(message);
	}
}
