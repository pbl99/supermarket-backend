package com.palmen.supermarket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDTO {

	private String username;
	private String password;
}
