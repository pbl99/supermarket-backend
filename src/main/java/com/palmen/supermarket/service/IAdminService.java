package com.palmen.supermarket.service;

import com.palmen.supermarket.dto.RegisterUserDTO;

public interface IAdminService {

	Boolean createAdmin(RegisterUserDTO registerUserDTO);
}
