package com.palmen.supermarket.service;

import com.palmen.supermarket.dto.LoginDTO;
import com.palmen.supermarket.dto.RegisterUserDTO;

public interface IAuthService {

	Boolean registerUser(RegisterUserDTO registerUserDTO);

	String login(LoginDTO loginDto);
}
