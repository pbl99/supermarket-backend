package com.palmen.supermarket.service.impl;

import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.palmen.supermarket.persistence.entity.Admin;
import com.palmen.supermarket.persistence.entity.Customer;
import com.palmen.supermarket.persistence.entity.Employee;
import com.palmen.supermarket.persistence.entity.User;
import com.palmen.supermarket.persistence.repository.IAdminRepository;
import com.palmen.supermarket.persistence.repository.ICustomerRepository;
import com.palmen.supermarket.persistence.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;

//Esta clase es la implementación concreta de UserDetailsService al estar marcado con component cada vez que se vea la interfaz por ahí implementara esta clase.
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final ICustomerRepository customerRepository;
	private final IEmployeeRepository employeeRepository;
	private final IAdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Buscar en cada repositorio según el tipo
		if (customerRepository.existsByUsername(username)) {
			Customer customer = customerRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
			return buildUserDetails(customer);
		} else if (employeeRepository.existsByUsername(username)) {
			Employee employee = employeeRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Employee not found"));
			return buildUserDetails(employee);
		} else if (adminRepository.existsByUsername(username)) {
			Admin admin = adminRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
			return buildUserDetails(admin);
		} else {
			throw new UsernameNotFoundException("User not found");
		}
	}

	private UserDetails buildUserDetails(User user) {
		  GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());

		    // Retornar el UserDetails con la información del usuario
		    return new org.springframework.security.core.userdetails.User(
		            user.getUsername(), 
		            user.getPassword(), 
		            Collections.singleton(authority) // Usar un Set con un único elemento
		    );
	}
}
