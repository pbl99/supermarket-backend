package com.palmen.supermarket.order.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.palmen.supermarket.order.dto.PurchaseDTO;
import com.palmen.supermarket.order.mapper.IPurchaseMapper;
import com.palmen.supermarket.order.persistence.repository.IPurchaseRepository;
import com.palmen.supermarket.order.service.IPurchaseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements IPurchaseService {

	private final IPurchaseRepository purchaseRepository;
	private final IPurchaseMapper purchaseMapper;

	@Transactional
	@Override
	public Boolean createPurchase(PurchaseDTO purchaseDTO) {
		return purchaseRepository.save(purchaseMapper.purchaseDTOToPurchaseEntity(purchaseDTO)) != null;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<PurchaseDTO> findAllPurchases(Long customerId){
		return purchaseRepository.findAllByCustomer(customerId)
				.stream()
				.map(purchaseMapper::purchaseEntityToPurchaseDTO)
				.toList();
	}
	
	@Transactional(readOnly = true)
	@Override
	public PurchaseDTO findPurchaseById(Long purchaseId) {
		return purchaseMapper.purchaseEntityToPurchaseDTO(purchaseRepository.findById(purchaseId).orElseThrow());
	}
}
