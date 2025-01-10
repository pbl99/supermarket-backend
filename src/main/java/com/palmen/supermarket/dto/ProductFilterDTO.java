package com.palmen.supermarket.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductFilterDTO {

	private String name;
	private Double basePrice;
	private LocalDate expirationDate;
}
