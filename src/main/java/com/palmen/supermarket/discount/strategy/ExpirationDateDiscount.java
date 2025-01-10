package com.palmen.supermarket.discount.strategy;

import java.time.LocalDate;

import com.palmen.supermarket.persistence.entity.Product;

public class ExpirationDateDiscount implements DiscountStrategy{

	 	@Override
	    public double applyDiscount(Product product, Double basePrice) {
	        if (product.getExpirationDate() != null &&
	            product.getExpirationDate().isBefore(LocalDate.now().plusDays(3))) {
	            System.out.println("Descuento del 20% aplicado por fecha de caducidad cercana.");
	            return basePrice * 0.8; 
	        }
	        return basePrice; 
	    }
}
