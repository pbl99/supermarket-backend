package com.palmen.supermarket.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.palmen.supermarket.user.dto.RegisterUserDTO;
import com.palmen.supermarket.user.mapper.IEmployeeMapper;
import com.palmen.supermarket.user.persistence.entity.Employee;
import com.palmen.supermarket.user.persistence.entity.Role;
import com.palmen.supermarket.user.persistence.repository.IEmployeeRepository;
import com.palmen.supermarket.user.persistence.repository.IRoleRepository;
import com.palmen.supermarket.user.service.IEmployeeService;
import com.palmen.supermarket.user.service.IPasswordService;
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
