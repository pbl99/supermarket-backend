package com.palmen.supermarket.user.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.palmen.supermarket.user.service.IPasswordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements IPasswordService {

	private final PasswordEncoder passwordEncoder;

	@Override
	public String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
}
