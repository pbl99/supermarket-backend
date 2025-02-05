package com.palmen.supermarket.product.strategy;

import com.palmen.supermarket.product.persistence.entity.Product;

public class QuantityDiscount implements DiscountStrategy{

	@Override
	public double applyDiscount(Product product, Double basePrice) {
		
		return 0;
	}

}
