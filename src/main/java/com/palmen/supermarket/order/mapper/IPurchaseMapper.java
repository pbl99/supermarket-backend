package com.palmen.supermarket.order.mapper;

import org.mapstruct.Mapper;
import com.palmen.supermarket.order.dto.PurchaseDTO;
import com.palmen.supermarket.order.persistence.entity.Purchase;

@Mapper(componentModel = "spring")
public interface IPurchaseMapper {

	Purchase purchaseDTOToPurchaseEntity(PurchaseDTO purchaseDTO);
	PurchaseDTO purchaseEntityToPurchaseDTO(Purchase purchase);
}
