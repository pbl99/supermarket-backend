package com.palmen.supermarket.product.strategy;

import com.palmen.supermarket.product.persistence.entity.Product;

public interface DiscountStrategy {

	double applyDiscount(Product product, Double basePrice);
}
