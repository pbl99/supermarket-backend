package com.palmen.supermarket.cart.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.palmen.supermarket.cart.dto.CartDTO;
import com.palmen.supermarket.cart.dto.CartItemDTO;
import com.palmen.supermarket.cart.exception.CartNotFoundException;
import com.palmen.supermarket.cart.mapper.ICartMapper;
import com.palmen.supermarket.cart.persistence.entity.Cart;
import com.palmen.supermarket.cart.persistence.entity.CartItem;
import com.palmen.supermarket.cart.persistence.repository.ICartItemRepository;
import com.palmen.supermarket.cart.persistence.repository.ICartRepository;
import com.palmen.supermarket.cart.service.ICartService;
import com.palmen.supermarket.product.dto.ProductDTO;
import com.palmen.supermarket.product.service.IProductService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {

	private final ICartRepository cartRepository;
	private final ICartItemRepository cartItemRepository;
	private final IProductService productService;
	private final ICartMapper cartMapper;

	@Transactional
	@Override
	public Boolean createCart(CartDTO cartDTO) {
		return cartRepository.save(cartMapper.cartDTOToCart(cartDTO)) != null;
	}
	
	//Revisar enfoque de acoplar el servicio de producto 
	@Transactional
	@Override
	public Boolean addProductsToCart(Long customerId, CartItemDTO cartItemDTO) {
		 Cart cart = cartRepository.findById(customerId)
	                .orElseThrow(() -> new CartNotFoundException("Cart not found for customer with ID: " + customerId));

	     ProductDTO productDTO = productService.findProduct(cartItemDTO.getProductId());

	     CartItem cartItem = CartItem.builder()
	             .cart(cart)
	             .productId(productDTO.getId())
	             .productName(productDTO.getName())
	             .productPrice(productDTO.getPrice())
	             .quantity(cartItemDTO.getQuantity())
	             .build();

	     return cartItemRepository.save(cartItem) != null;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Cart findCart(Long customerId) {
		return cartRepository.findById(customerId).orElseThrow();
	}
	
	
	@Transactional
	@Override
	public Boolean dropCart(Long customerId) {
		Cart cart = cartRepository.findById(customerId)
				.orElseThrow(() -> new CartNotFoundException("Cart not found for customer with ID: " + customerId));

		cart.getItems().clear(); 

		return cartRepository.save(cart) != null;
	}
	
	@Transactional
	@Override
	public Boolean removeProduct(Long productId) {
		if (cartItemRepository.existsById(productId)) {
			cartItemRepository.deleteById(productId);
			return true;
		}
		return false;
	}

}
