package com.palmen.supermarket.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileInformationDTO {

	private String telephoneNumber;
	private String dni;
	private String address;
}
