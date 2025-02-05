package com.palmen.supermarket.mock.entity;

import com.palmen.supermarket.mock.MockCreator;
import com.palmen.supermarket.user.persistence.entity.Customer;

public class MockCustomer implements MockCreator<Customer>{

	@Override
	public Customer create() {
		 return Customer.builder()
	                .id(1L)
	                .username("pbl")
	                .email("pbl@customer.com")
	                .password("12345")
	                .address("Calle Sacramento 10, 1-DERECHA")
	                .dni("71735013Z")
	                .email("pbl99@gmail.com")
	                .build();
	}

}
