package com.palmen.supermarket.mapper;

import org.mapstruct.Mapper;
import com.palmen.supermarket.dto.CustomerDTO;
import com.palmen.supermarket.dto.RegisterUserDTO;
import com.palmen.supermarket.persistence.entity.Customer;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {

	Customer customerDTOToCustomer(CustomerDTO customerDTO);
	
	CustomerDTO customerToCustomerDTO(Customer customer);
	
	Customer registerUserDTOToCustomer(RegisterUserDTO registerUserDTO);
}
