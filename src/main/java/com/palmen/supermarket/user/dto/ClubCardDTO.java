package com.palmen.supermarket.user.dto;

import com.palmen.supermarket.user.persistence.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubCardDTO {

	private Long id;
	private String cardNumber;
	private Integer points;
	private Customer customer;
}
