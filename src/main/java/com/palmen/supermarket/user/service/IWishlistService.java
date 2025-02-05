package com.palmen.supermarket.user.service;

import com.palmen.supermarket.user.dto.WishlistDTO;
import com.palmen.supermarket.user.dto.WishlistItemDTO;
import com.palmen.supermarket.user.persistence.entity.Wishlist;

public interface IWishlistService {

	Boolean createWishlist(WishlistDTO wishlistDTO);

	Boolean addProductsToWishlist(Long customerId, WishlistItemDTO wishlistItemDTO);

	Wishlist findWishlist(Long customerId);

	Boolean dropWishlist(Long customerId);

	Boolean removeWishlistItem(Long wishlistItemId);
}
