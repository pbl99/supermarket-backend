package com.palmen.supermarket.discount.strategy;

import com.palmen.supermarket.persistence.entity.Product;
import com.palmen.supermarket.persistence.entity.ProductType;

public class ProductTypeDiscount implements DiscountStrategy {

	@Override
	public double applyDiscount(Product product, Double basePrice) {
		if (product.getProductType() == ProductType.PERISHABLE) {
			System.out.println("Descuento del 10% aplicado para productos perecederos.");
			return basePrice * 0.9;
		}
		return basePrice;
	}

}
