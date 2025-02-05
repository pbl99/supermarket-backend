package com.palmen.supermarket.user.service;

import java.util.List;

import com.palmen.supermarket.user.dto.CustomerDTO;
import com.palmen.supermarket.user.dto.CustomerProfileInformationDTO;
import com.palmen.supermarket.user.dto.RegisterUserDTO;

public interface ICustomerService {

	Boolean createCustomer(RegisterUserDTO registerUserDTO);

	Boolean deleteUser(Long id);

	CustomerDTO findCustomer(Long id);

	List<CustomerDTO> findAllCustomers();

	Boolean updateProfileInformation(CustomerProfileInformationDTO customerProfileInformationDTO, Long id);
	
	Boolean enabledDigitalTicket(Long id);
	
	Boolean enabledClubCard(Long id);
}
