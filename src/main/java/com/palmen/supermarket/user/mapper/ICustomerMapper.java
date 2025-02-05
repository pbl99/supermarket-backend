package com.palmen.supermarket.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.palmen.supermarket.user.dto.CustomerDTO;
import com.palmen.supermarket.user.dto.RegisterUserDTO;
import com.palmen.supermarket.user.persistence.entity.Customer;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {

	Customer customerDTOToCustomer(CustomerDTO customerDTO);
	
	CustomerDTO customerToCustomerDTO(Customer customer);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "cart", ignore = true)
	@Mapping(target = "clubCard", ignore = true)
	@Mapping(target = "clubCardEnabled", ignore = true)
	@Mapping(target = "paymentMethods", ignore = true)
	@Mapping(target = "ticketEnabled", ignore = true)
	@Mapping(target = "wishlist", ignore = true)
	@Mapping(target = "role", ignore = true)
	Customer registerUserDTOToCustomer(RegisterUserDTO registerUserDTO);
}
