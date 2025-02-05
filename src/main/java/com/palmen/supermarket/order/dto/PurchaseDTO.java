package com.palmen.supermarket.order.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDTO {
	private Long id;
	private List<PurchaseItemDTO> purchases;
	private Double totalPrice;
	private LocalDateTime purchaseDate;
}
