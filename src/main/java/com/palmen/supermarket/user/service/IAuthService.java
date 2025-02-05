package com.palmen.supermarket.user.service;

import com.palmen.supermarket.user.dto.LoginDTO;
import com.palmen.supermarket.user.dto.RegisterUserDTO;

public interface IAuthService {

	Boolean registerUser(RegisterUserDTO registerUserDTO);

	String login(LoginDTO loginDto);
}
