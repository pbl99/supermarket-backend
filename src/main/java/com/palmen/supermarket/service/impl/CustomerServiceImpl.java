package com.palmen.supermarket.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.palmen.supermarket.dto.CustomerDTO;
import com.palmen.supermarket.dto.RegisterUserDTO;
import com.palmen.supermarket.mapper.ICustomerMapper;
import com.palmen.supermarket.persistence.entity.Customer;
import com.palmen.supermarket.persistence.entity.Role;
import com.palmen.supermarket.persistence.repository.ICustomerRepository;
import com.palmen.supermarket.persistence.repository.IRoleRepository;
import com.palmen.supermarket.service.ICustomerService;
import com.palmen.supermarket.service.IPasswordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

	private final ICustomerRepository customerRepository;
	private final IRoleRepository roleRepository;
	private final ICustomerMapper customerMapper;
	private final IPasswordService passwordService;

	@Transactional
	@Override
	public Boolean createCustomer(RegisterUserDTO registerUserDTO) {
		Role role = roleRepository.findByName(registerUserDTO.getRole())
				.orElseThrow(() -> new RuntimeException("Role not found: " + registerUserDTO.getRole()));

		Customer customer = customerMapper.registerUserDTOToCustomer(registerUserDTO);
		customer.setRole(role);
		customer.setPassword(passwordService.encodePassword(customer.getPassword()));

		return customerRepository.save(customer) != null;
	}

	@Transactional
	@Override
	public Boolean deleteUser(Long id) {
		if (customerRepository.existsById(id)) {
			customerRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	@Override
	public CustomerDTO findCustomer(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow();

		return customerMapper.customerToCustomerDTO(customer);
	}

	@Transactional(readOnly = true)
	@Override
	public List<CustomerDTO> findAllCustomers() {
		List<CustomerDTO> customers = customerRepository.findAll().stream().map(customerMapper::customerToCustomerDTO)
				.toList();

		return customers;
	}

}
