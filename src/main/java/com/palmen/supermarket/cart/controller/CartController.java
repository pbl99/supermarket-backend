package com.palmen.supermarket.cart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palmen.supermarket.cart.dto.CartItemDTO;
import com.palmen.supermarket.cart.service.ICartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

	private final ICartService cartService;

	@PostMapping("/{customerId}/cart/add")
	public ResponseEntity<?> addProductsToCart(@PathVariable Long customerId, @RequestBody CartItemDTO cartItemDTO) {
		Boolean productAdded = cartService.addProductsToCart(customerId, cartItemDTO);

		return productAdded ? ResponseEntity.status(HttpStatus.CREATED).body(cartItemDTO)
				: ResponseEntity.status(HttpStatus.CONFLICT).body("Product not added to cart");
	}

	@PostMapping("/{customerId}/cart/dropCart")
	public ResponseEntity<?> dropCart(@PathVariable Long customerId) {
		Boolean cartDropped = cartService.dropCart(customerId);

		return cartDropped ? ResponseEntity.status(HttpStatus.OK).body(cartDropped)
				: ResponseEntity.status(HttpStatus.CONFLICT).body("Cart not dropped");
	}

	@PostMapping("/{customerId/cart/removeProduct}")
	public ResponseEntity<?> removeProduct(@PathVariable Long productId) {
		Boolean productRemoved = cartService.removeProduct(productId);

		return productRemoved ? ResponseEntity.status(HttpStatus.OK).body(productRemoved)
				: ResponseEntity.status(HttpStatus.CONFLICT).body("Product not removed");
	}
}
