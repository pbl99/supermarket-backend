package com.palmen.supermarket.user.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.palmen.supermarket.cart.persistence.entity.Cart;
import com.palmen.supermarket.user.dto.CustomerDTO;
import com.palmen.supermarket.user.dto.CustomerProfileInformationDTO;
import com.palmen.supermarket.user.dto.RegisterUserDTO;
import com.palmen.supermarket.user.mapper.ICustomerMapper;
import com.palmen.supermarket.user.persistence.entity.Customer;
import com.palmen.supermarket.user.persistence.entity.Role;
import com.palmen.supermarket.user.persistence.entity.Wishlist;
import com.palmen.supermarket.user.persistence.repository.ICustomerRepository;
import com.palmen.supermarket.user.persistence.repository.IRoleRepository;
import com.palmen.supermarket.user.service.ICustomerService;
import com.palmen.supermarket.user.service.IPasswordService;
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

		// Cuidado con el acoplamiento en este diseño del cart
		Cart cart = new Cart();
		Wishlist wishlist = new Wishlist();
		Customer customer = customerMapper.registerUserDTOToCustomer(registerUserDTO);
		customer.setRole(role);
		customer.setCart(cart);
		customer.setWishlist(wishlist);
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

	@Transactional
	@Override
	public Boolean updateProfileInformation(CustomerProfileInformationDTO customerProfileInformationDTO, Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow();

		customer.setTelephoneNumber(customerProfileInformationDTO.getTelephoneNumber());
		customer.setDni(customerProfileInformationDTO.getDni());
		customer.setAddress(customerProfileInformationDTO.getAddress());

		return customerRepository.save(customer) != null;
	}
	
	@Transactional
	@Override
	public Boolean enabledDigitalTicket(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow();
		
		customer.setTicketEnabled(true);
		
		return customerRepository.save(customer) != null;
	}

	@Transactional
	@Override
	public Boolean enabledClubCard(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow();
		
		customer.setClubCardEnabled(true);
		
		return customerRepository.save(customer) != null;
	}
}
