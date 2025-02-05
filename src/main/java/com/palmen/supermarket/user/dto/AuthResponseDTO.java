package com.palmen.supermarket.user.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class AuthResponseDTO {

	private String accessToken;
}
