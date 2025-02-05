package com.palmen.supermarket.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palmen.supermarket.user.dto.AuthResponseDTO;
import com.palmen.supermarket.user.dto.LoginDTO;
import com.palmen.supermarket.user.dto.RegisterUserDTO;
import com.palmen.supermarket.user.service.IAuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final IAuthService authService;

	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
		Boolean resultRegister = authService.registerUser(registerUserDTO);

		return resultRegister ? ResponseEntity.status(HttpStatus.CREATED).body(resultRegister)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
	}

	@PostMapping("/login")
	public AuthResponseDTO loginUser(@RequestBody LoginDTO loginDTO) {
		String token = authService.login(loginDTO);
		return AuthResponseDTO.builder().accessToken(token).build();
	}
}
