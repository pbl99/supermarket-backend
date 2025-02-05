package com.palmen.supermarket.order.service;

import java.util.List;

import com.palmen.supermarket.order.dto.PurchaseDTO;

public interface IPurchaseService {

	Boolean createPurchase(PurchaseDTO purchaseDTO);
	List<PurchaseDTO> findAllPurchases(Long customerId);
	PurchaseDTO findPurchaseById(Long purchaseId);
}
