package com.palmen.supermarket.discount.strategy;

import com.palmen.supermarket.persistence.entity.Product;

public class QuantityDiscount implements DiscountStrategy{

	@Override
	public double applyDiscount(Product product, Double basePrice) {
		
		return 0;
	}

}
