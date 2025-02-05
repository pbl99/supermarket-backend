package com.palmen.supermarket.mock.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import com.palmen.supermarket.cart.persistence.entity.Cart;
import com.palmen.supermarket.cart.persistence.entity.CartItem;
import com.palmen.supermarket.mock.MockCreator;
import com.palmen.supermarket.user.persistence.entity.Customer;


public class MockCart implements MockCreator<Cart> {

	  @Override
	    public Cart create() {
	        Cart cart = Cart.builder()
	                .id(1L)
	                .customer(new Customer())
	                .items(new ArrayList<>()) 
	                .createdAt(LocalDateTime.now())
	                .updatedAt(LocalDateTime.now())
	                .checkedOut(false)
	                .build();
	    
	        CartItem cartItem = new MockCartItem().create();
	        Customer customer = new MockCustomer().create();
	        
	        cart.getItems().add(cartItem);
	        cart.setCustomer(customer);
	        return cart;
	    }

}
