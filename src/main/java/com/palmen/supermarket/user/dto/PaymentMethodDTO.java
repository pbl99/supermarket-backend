package com.palmen.supermarket.user.dto;

import com.palmen.supermarket.user.persistence.entity.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethodDTO {
	private Long id;
	private Long customerId;
	private PaymentType type;
	private String details;
	private Boolean isDefault;
}
