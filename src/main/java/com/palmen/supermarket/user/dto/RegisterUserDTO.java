package com.palmen.supermarket.user.dto;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserDTO {

	private String username;
	private String email;
	private String password;
	private String role;
}
