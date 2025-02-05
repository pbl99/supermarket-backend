package com.palmen.supermarket.cart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.palmen.supermarket.cart.dto.CartDTO;
import com.palmen.supermarket.cart.dto.CartItemDTO;
import com.palmen.supermarket.cart.exception.CartNotFoundException;
import com.palmen.supermarket.cart.mapper.ICartMapper;
import com.palmen.supermarket.cart.persistence.entity.Cart;
import com.palmen.supermarket.cart.persistence.entity.CartItem;
import com.palmen.supermarket.cart.persistence.repository.ICartItemRepository;
import com.palmen.supermarket.cart.persistence.repository.ICartRepository;
import com.palmen.supermarket.cart.service.impl.CartServiceImpl;
import com.palmen.supermarket.mock.entity.MockCart;
import com.palmen.supermarket.mock.entity.MockCartItem;
import com.palmen.supermarket.product.dto.ProductDTO;
import com.palmen.supermarket.product.service.IProductService;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

	private ICartRepository cartRepository;
	private ICartItemRepository cartItemRepository;
	private IProductService productService;
	private ICartMapper cartMapper;
	private CartServiceImpl cartServiceImpl;
	private Cart cart;
	private CartItem cartItem;
	
	@BeforeEach
	void setup() {
		cartRepository = Mockito.mock(ICartRepository.class);
		cartItemRepository = Mockito.mock(ICartItemRepository.class);
		productService = Mockito.mock(IProductService.class);
		cartMapper = Mockito.mock(ICartMapper.class);
		cartServiceImpl = new CartServiceImpl(cartRepository, cartItemRepository, productService, cartMapper);
		cart = new MockCart().create();
		cartItem = new MockCartItem().create();
	}
	
	@Test
	void createCartSuccessfully_Test() {
		CartDTO cartDTO = new CartDTO();
		
		when(cartMapper.cartDTOToCart(cartDTO)).thenReturn(cart);
		when(cartRepository.save(cart)).thenReturn(cart);
		
		Boolean result = cartServiceImpl.createCart(cartDTO);
		
		assertTrue(result);
		
		verify(cartMapper).cartDTOToCart(cartDTO);
		verify(cartRepository).save(cart);
	}
	
	@Test
	void createCartNull_Test() {
		CartDTO cartDTO = new CartDTO();
		
		when(cartMapper.cartDTOToCart(cartDTO)).thenReturn(cart);
		when(cartRepository.save(cart)).thenReturn(null);
		
		Boolean result = cartServiceImpl.createCart(cartDTO);
		
		assertFalse(result);
		
		verify(cartMapper).cartDTOToCart(cartDTO);
		verify(cartRepository).save(cart);
	}
	
	@Test
	void addProductsToCartSuccessfully_Test() {
		Long customerId = (long) 1;
		
		CartItemDTO cartItemDTO = CartItemDTO.builder()
	            .productId(1L)
	            .quantity(2)
	            .build();
		
	    ProductDTO productDTO = ProductDTO.builder()
	            .id(1L)
	            .name("Triptofano")
	            .price(2.55)
	            .build();
		
		when(cartRepository.findById(customerId)).thenReturn(Optional.of(cart));
		when(productService.findProduct(cartItemDTO.getProductId())).thenReturn(productDTO);
		when(cartItemRepository.save(any(CartItem.class))).thenReturn(cartItem);
		
		Boolean result = cartServiceImpl.addProductsToCart(customerId, cartItemDTO);
		
	    assertTrue(result);

	    ArgumentCaptor<CartItem> captor = ArgumentCaptor.forClass(CartItem.class);
	    verify(cartItemRepository).save(captor.capture());
	    CartItem capturedCartItem = captor.getValue();
	    
	    assertEquals(cart.getId(), capturedCartItem.getCart().getId());
	    assertEquals(1L, capturedCartItem.getProductId());
	    assertEquals("Triptofano", capturedCartItem.getProductName());
	    assertEquals(2.55, capturedCartItem.getProductPrice());
	    assertEquals(2, capturedCartItem.getQuantity());
	}
	
	@Test
	void addProductsToCartNotFoundException_Test() {
	    Long customerId = 1L;

	    CartItemDTO cartItemDTO = CartItemDTO.builder()
	            .productId(1L)
	            .quantity(2)
	            .build();

	    when(cartRepository.findById(customerId))
	            .thenThrow(new CartNotFoundException("Cart not found for customer with ID: " + customerId));

	
	    CartNotFoundException exception = assertThrows(CartNotFoundException.class, () -> {
	        cartServiceImpl.addProductsToCart(customerId, cartItemDTO);
	    });

	    assertEquals("Cart not found for customer with ID: " + customerId, exception.getMessage());

	    verify(cartRepository).findById(customerId);
	    verifyNoMoreInteractions(cartRepository, cartItemRepository, productService);
	}

	@Test
	void addProductsToCartNull_Test() {
		Long customerId = 1L;

		CartItemDTO cartItemDTO = CartItemDTO.builder()
				.productId(1L)
				.quantity(2)
				.build();
		
		ProductDTO productDTO = ProductDTO.builder()
		        .id(1L)
		        .name("Triptofano")
		        .price(2.55)
		        .build();

		when(cartRepository.findById(customerId)).thenReturn(Optional.of(cart));
		when(productService.findProduct(cartItemDTO.getProductId())).thenReturn(productDTO);
		when(cartItemRepository.save(any(CartItem.class))).thenReturn(null);

		Boolean result = cartServiceImpl.addProductsToCart(customerId, cartItemDTO);
		
		assertFalse(result);
	}
	
	@Test
	void findCartSuccesfully_Test() {
		Long customerId = 1L;
		
		when(cartRepository.findById(customerId)).thenReturn(Optional.of(cart));
		
		Cart cart = cartServiceImpl.findCart(customerId);
		
		assertNotNull(cart);
		assertEquals("71735013Z", cart.getCustomer().getDni());
		assertEquals(1, cart.getId());
		assertEquals("Triptofano", cart.getItems().get(0).getProductName());
		
		verify(cartRepository).findById(customerId);
	}
	
	@Test
	void dropCartSuccesfully_Test() {
		Long customerId = 1L;
		
		when(cartRepository.findById(customerId)).thenReturn(Optional.of(cart));
		when(cartRepository.save(cart)).thenReturn(cart);
		
		Boolean result = cartServiceImpl.dropCart(customerId);
		
		assertTrue(result);
		assertTrue(cart.getItems().isEmpty());
		
		verify(cartRepository).findById(customerId);
		verify(cartRepository).save(cart);
	}
	
	@Test
	void dropCartFail_Test() {
		Long customerId = 1L;
		
		when(cartRepository.findById(customerId)).thenReturn(Optional.of(cart));
		when(cartRepository.save(cart)).thenReturn(null);
		
		Boolean result = cartServiceImpl.dropCart(customerId);
		
		assertFalse(result);
		
		verify(cartRepository).findById(customerId);
		verify(cartRepository).save(cart);
	}
	
	@Test
	void dropCartNotFoundException_Test() {
		Long customerId = 1L;
		
		CartItemDTO cartItemDTO = CartItemDTO.builder()
				.productId(1L)
				.quantity(2)
				.build();
		
		when(cartRepository.findById(customerId))
				.thenThrow(new CartNotFoundException("Cart not found for customer with ID: " + customerId));

		CartNotFoundException exception = assertThrows(CartNotFoundException.class, () -> {
			cartServiceImpl.addProductsToCart(customerId, cartItemDTO);
		});

		assertEquals("Cart not found for customer with ID: " + customerId, exception.getMessage());

		verify(cartRepository).findById(customerId);
		verifyNoMoreInteractions(cartRepository, cartItemRepository, productService);
	}
	
	@Test
	void removeProductSuccesfully_Test() {
		Long productId = 1L;
		
		when(cartItemRepository.existsById(productId)).thenReturn(true);
		
		Boolean result = cartServiceImpl.removeProduct(productId);
		
		assertTrue(result);
	}
	
	@Test
	void removeProductsFail_Test() {
		Long productId = 1L;
		
		when(cartItemRepository.existsById(productId)).thenReturn(false);
		
		Boolean result = cartServiceImpl.removeProduct(productId);
		
		assertFalse(result);
	}
}
