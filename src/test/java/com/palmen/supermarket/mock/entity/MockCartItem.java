package com.palmen.supermarket.mock.entity;

import com.palmen.supermarket.cart.persistence.entity.Cart;
import com.palmen.supermarket.cart.persistence.entity.CartItem;
import com.palmen.supermarket.mock.MockCreator;
import com.palmen.supermarket.user.persistence.entity.Customer;

public class MockCartItem implements MockCreator<CartItem>{

	@Override
	public CartItem create() {
		return CartItem.builder()
				.id(1L)
				.cart(Cart.builder()
						.id(1L)
						.customer(Customer
								.builder()
								.id(1L)
								.username("pbl99")
								.build())
						.build())
				.productId(1L)
				.productName("Triptofano")
				.productPrice(2.55)
				.quantity(2)
				.totalPrice(5.10)
				.build();
	}

}
