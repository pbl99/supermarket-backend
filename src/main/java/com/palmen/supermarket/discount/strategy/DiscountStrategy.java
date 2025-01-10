package com.palmen.supermarket.discount.strategy;

import com.palmen.supermarket.persistence.entity.Product;

public interface DiscountStrategy {

	double applyDiscount(Product product, Double basePrice);
}
