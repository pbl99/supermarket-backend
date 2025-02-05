package com.palmen.supermarket.user.service;

import com.palmen.supermarket.user.dto.RegisterUserDTO;

public interface IAdminService {

	Boolean createAdmin(RegisterUserDTO registerUserDTO);
}
