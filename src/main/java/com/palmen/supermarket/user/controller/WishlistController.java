package com.palmen.supermarket.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palmen.supermarket.user.dto.WishlistItemDTO;
import com.palmen.supermarket.user.service.IWishlistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wishlist")
public class WishlistController {

	private final IWishlistService wishlistService;

	@PostMapping("/{customerId}/wishlist/add")
	public ResponseEntity<?> addProductsToWishlist(@PathVariable Long customerId,
			@RequestBody WishlistItemDTO wishlistItemDTO) {
		Boolean productAdded = wishlistService.addProductsToWishlist(customerId, wishlistItemDTO);

		return productAdded ? ResponseEntity.status(HttpStatus.CREATED).body(wishlistItemDTO)
				: ResponseEntity.status(HttpStatus.CONFLICT).body("Product not added to wishlist");
	}

	@PostMapping("/{customerId}/wishlist/dropWishlist")
	public ResponseEntity<?> dropWishlist(@PathVariable Long customerId) {
		Boolean wishlistDropped = wishlistService.dropWishlist(customerId);

		return wishlistDropped ? ResponseEntity.status(HttpStatus.OK).body(wishlistDropped)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wishlist not dropped");
	}

	@PostMapping("/{customerId}/wishlist/removeWishlistItem")
	public ResponseEntity<?> removeWishlistItem(@PathVariable Long wishlistItemId) {
		Boolean wishlistItemRemoved = wishlistService.removeWishlistItem(wishlistItemId);

		return wishlistItemRemoved ? ResponseEntity.status(HttpStatus.OK).body(wishlistItemRemoved)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("WishlistItrem not removed");
	}

}
