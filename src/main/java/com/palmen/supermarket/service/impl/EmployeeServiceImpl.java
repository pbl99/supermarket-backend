package com.palmen.supermarket.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.palmen.supermarket.dto.RegisterUserDTO;
import com.palmen.supermarket.mapper.IEmployeeMapper;
import com.palmen.supermarket.persistence.entity.Employee;
import com.palmen.supermarket.persistence.entity.Role;
import com.palmen.supermarket.persistence.repository.IEmployeeRepository;
import com.palmen.supermarket.persistence.repository.IRoleRepository;
import com.palmen.supermarket.service.IEmployeeService;
import com.palmen.supermarket.service.IPasswordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

	private final IEmployeeRepository employeeRepository;
	private final IRoleRepository roleRepository;
	private final IEmployeeMapper employeeMapper;
	private final IPasswordService passwordService;

	@Transactional
	@Override
	public Boolean createEmployee(RegisterUserDTO registerUserDTO) {
		Role role = roleRepository.findByName(registerUserDTO.getRole())
				.orElseThrow(() -> new RuntimeException("Role not found: " + registerUserDTO.getRole()));

		Employee employee = employeeMapper.registerUserDTOToEmployee(registerUserDTO);
		employee.setRole(role);
		employee.setPassword(passwordService.encodePassword(employee.getPassword()));

		return employeeRepository.save(employee) != null;
	}

}
