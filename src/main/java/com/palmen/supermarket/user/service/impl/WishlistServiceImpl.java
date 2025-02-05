package com.palmen.supermarket.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.palmen.supermarket.product.dto.ProductDTO;
import com.palmen.supermarket.product.service.IProductService;
import com.palmen.supermarket.user.dto.WishlistDTO;
import com.palmen.supermarket.user.dto.WishlistItemDTO;
import com.palmen.supermarket.user.exception.WishlistNotFoundException;
import com.palmen.supermarket.user.mapper.IWishlistMapper;
import com.palmen.supermarket.user.persistence.entity.Wishlist;
import com.palmen.supermarket.user.persistence.entity.WishlistItem;
import com.palmen.supermarket.user.persistence.repository.IWishlistItemRepository;
import com.palmen.supermarket.user.persistence.repository.IWishlistRepository;
import com.palmen.supermarket.user.service.IWishlistService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements IWishlistService{
	
	private final IWishlistRepository wishlistRepository;
	private final IWishlistItemRepository wishlistItemRepository;
	private final IWishlistMapper wishlistMapper;
	private final IProductService productService;

	@Transactional
	@Override
	public Boolean createWishlist(WishlistDTO wishlistDTO) {
		return wishlistRepository.save(wishlistMapper.wishlistDTOToWishlist(wishlistDTO)) != null;
	}
	
	//Revisar enfoque de acoplar el servicio de producto 
	@Transactional
	@Override
	public Boolean addProductsToWishlist(Long customerId, WishlistItemDTO wishlistItemDTO) {
		 Wishlist wishlist = wishlistRepository.findById(customerId)
	                .orElseThrow(() -> new WishlistNotFoundException("Wishlist not found for customer with ID: " + customerId));

	     ProductDTO productDTO = productService.findProduct(wishlistItemDTO.getProductId());

	     WishlistItem wishlistItem = WishlistItem.builder()
	             .wishlist(wishlist)
	             .productId(productDTO.getId())
	             .productName(productDTO.getName())
	             .productPrice(productDTO.getPrice())
	             .build();

	     return wishlistItemRepository.save(wishlistItem) != null;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Wishlist findWishlist(Long customerId) {
		return wishlistRepository.findById(customerId).orElseThrow(
				() -> new WishlistNotFoundException("Wishlist not found for customer with ID: " + customerId));
	}

	@Transactional
	@Override
	public Boolean dropWishlist(Long customerId) {
		Wishlist wishlist = wishlistRepository.findById(customerId).orElseThrow(
				() -> new WishlistNotFoundException("Wishlist not found for customer with ID: " + customerId));

		wishlist.getWishlistItem().clear();

		return wishlistRepository.save(wishlist) != null;
	}

	@Transactional
	@Override
	public Boolean removeWishlistItem(Long wishlistItemId) {

		if (wishlistItemRepository.existsById(wishlistItemId)) {
			wishlistItemRepository.deleteById(wishlistItemId);
			return true;
		}
		return false;
	}
}
