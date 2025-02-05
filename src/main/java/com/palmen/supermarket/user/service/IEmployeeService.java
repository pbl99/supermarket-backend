package com.palmen.supermarket.user.service;

import com.palmen.supermarket.user.dto.RegisterUserDTO;

public interface IEmployeeService {

	Boolean createEmployee(RegisterUserDTO registerUserDTO);
}
