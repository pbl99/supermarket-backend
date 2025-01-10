package com.palmen.supermarket.service;

import com.palmen.supermarket.dto.RegisterUserDTO;

public interface IEmployeeService {

	Boolean createEmployee(RegisterUserDTO registerUserDTO);
}
