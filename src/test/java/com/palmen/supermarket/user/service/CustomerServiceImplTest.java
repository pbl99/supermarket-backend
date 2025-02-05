package com.palmen.supermarket.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.palmen.supermarket.mock.entity.MockCustomer;
import com.palmen.supermarket.user.dto.RegisterUserDTO;
import com.palmen.supermarket.user.mapper.ICustomerMapper;
import com.palmen.supermarket.user.persistence.entity.Customer;
import com.palmen.supermarket.user.persistence.entity.Role;
import com.palmen.supermarket.user.persistence.repository.ICustomerRepository;
import com.palmen.supermarket.user.persistence.repository.IRoleRepository;
import com.palmen.supermarket.user.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class CustomerServiceImplTest {

	private CustomerServiceImpl customerServiceImpl;
	private ICustomerRepository customerRepository;
	private IRoleRepository roleRepository;
	private ICustomerMapper customerMapper;
	private IPasswordService passwordService;
	private Customer customer;

	@BeforeEach
	void setup() {
		customerRepository = Mockito.mock(ICustomerRepository.class);
		customerMapper = Mockito.mock(ICustomerMapper.class);
		roleRepository = Mockito.mock(IRoleRepository.class);
		passwordService = Mockito.mock(IPasswordService.class);
		customerServiceImpl = new CustomerServiceImpl(customerRepository, roleRepository, customerMapper,
				passwordService);
		customer = new MockCustomer().create();
	}

	@Test
	void createCustomerSuccesFully_Test() {
		RegisterUserDTO registerUserDTO = new RegisterUserDTO();
		Role role = new Role();

		when(roleRepository.findByName(registerUserDTO.getRole())).thenReturn(Optional.of(role));
		when(customerMapper.registerUserDTOToCustomer(registerUserDTO)).thenReturn(customer);
		when(customerRepository.save(customer)).thenReturn(customer);

		Boolean result = customerServiceImpl.createCustomer(registerUserDTO);

		assertNotNull(result);
		assertTrue(result);

		verify(roleRepository).findByName(registerUserDTO.getRole());
		verify(customerMapper).registerUserDTOToCustomer(registerUserDTO);
		verify(customerRepository).save(customer);
	}

	@Test
	void createCustomerRoleNotFound_Test() {
		RegisterUserDTO registerUserDTO = new RegisterUserDTO();
		registerUserDTO.setRole("NON_EXISTENT_ROLE");

		when(roleRepository.findByName(registerUserDTO.getRole()))
				.thenThrow(new RuntimeException("Role not found: " + registerUserDTO.getRole()));

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			customerServiceImpl.createCustomer(registerUserDTO);
		});

		assertEquals("Role not found: NON_EXISTENT_ROLE", exception.getMessage());

		verify(roleRepository).findByName(registerUserDTO.getRole());
		verifyNoInteractions(customerMapper, customerRepository, passwordService);
	}
	
	@Test
	void createCustomerFail_Test() {
		RegisterUserDTO registerUserDTO = new RegisterUserDTO();
		Role role = new Role();

		when(roleRepository.findByName(registerUserDTO.getRole())).thenReturn(Optional.of(role));
		when(customerMapper.registerUserDTOToCustomer(registerUserDTO)).thenReturn(customer);
		when(customerRepository.save(customer)).thenReturn(null);

		Boolean result = customerServiceImpl.createCustomer(registerUserDTO);

		assertNotNull(result);
		assertFalse(result);

		verify(roleRepository).findByName(registerUserDTO.getRole());
		verify(customerMapper).registerUserDTOToCustomer(registerUserDTO);
		verify(customerRepository).save(customer);
	}
}
