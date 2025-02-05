package com.palmen.supermarket.cart.service;

import com.palmen.supermarket.cart.dto.CartDTO;
import com.palmen.supermarket.cart.dto.CartItemDTO;
import com.palmen.supermarket.cart.persistence.entity.Cart;


public interface ICartService {

	Boolean createCart(CartDTO cartDTO);

	Boolean addProductsToCart(Long customerId, CartItemDTO cartItemDTO);

	Cart findCart(Long customerId);

	Boolean dropCart(Long customerId);

	Boolean removeProduct(Long productId);
}
