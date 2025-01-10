package com.palmen.supermarket.service;

import java.util.List;
import com.palmen.supermarket.dto.CustomerDTO;
import com.palmen.supermarket.dto.RegisterUserDTO;

public interface ICustomerService {

	Boolean createCustomer(RegisterUserDTO registerUserDTO);

	Boolean deleteUser(Long id);

	CustomerDTO findCustomer(Long id);

	List<CustomerDTO> findAllCustomers();
}
