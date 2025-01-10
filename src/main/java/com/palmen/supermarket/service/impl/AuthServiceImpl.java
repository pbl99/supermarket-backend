package com.palmen.supermarket.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.palmen.supermarket.dto.LoginDTO;
import com.palmen.supermarket.dto.RegisterUserDTO;
import com.palmen.supermarket.security.jwt.JwtTokenProvider;
import com.palmen.supermarket.service.IAdminService;
import com.palmen.supermarket.service.IAuthService;
import com.palmen.supermarket.service.ICustomerService;
import com.palmen.supermarket.service.IEmployeeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final ICustomerService customerService;
	private final IEmployeeService employeeService;
	private final IAdminService adminService;

	@Override
	public Boolean registerUser(RegisterUserDTO registerUserDTO) {
		String role = registerUserDTO.getRole().toUpperCase();

		switch (role) {
		case "CUSTOMER":
			return customerService.createCustomer(registerUserDTO);
		case "EMPLOYEE":
			return employeeService.createEmployee(registerUserDTO);
		case "ADMIN":
			return adminService.createAdmin(registerUserDTO);
		default:
			throw new IllegalArgumentException("Invalid role: " + registerUserDTO.getRole());
		}
	}

	@Override
	public String login(LoginDTO loginDto) {

		// 01 - AuthenticationManager is used to authenticate the user
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);

		return token;
	}

}
