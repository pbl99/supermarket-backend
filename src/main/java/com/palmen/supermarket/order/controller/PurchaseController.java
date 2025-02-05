package com.palmen.supermarket.order.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.palmen.supermarket.order.dto.PurchaseDTO;
import com.palmen.supermarket.order.service.IPurchaseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/purchase")
public class PurchaseController {

	private final IPurchaseService purchaseService;
	
	@PostMapping("/createPurchase")
	public ResponseEntity<?> createPurchase(@RequestBody PurchaseDTO purchaseDTO){
		Boolean createdPurchase = purchaseService.createPurchase(purchaseDTO);
		
		return createdPurchase ? ResponseEntity.status(HttpStatus.CREATED).body("Purchase created succesfully")
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Purchase not created");
	}
	
	@GetMapping("/findAllPurchaseByCustomer/{customerId}")
	public ResponseEntity<?> findAllPurchasesByCustomer(@PathVariable Long customerId){
		List<PurchaseDTO> purchases = purchaseService.findAllPurchases(customerId);
		
		return !purchases.isEmpty() ? ResponseEntity.status(HttpStatus.FOUND).body("Purchases correctly fetch")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchases not found");
	}
	
	@GetMapping("/findPurchaseById")
	public ResponseEntity<?> findPurchaseById(@PathVariable Long purchaseId){
		PurchaseDTO purchase = purchaseService.findPurchaseById(purchaseId);
		
		return purchase != null ? ResponseEntity.status(HttpStatus.FOUND).body("Purchase correctly fetch")
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase not found");
	}
}
