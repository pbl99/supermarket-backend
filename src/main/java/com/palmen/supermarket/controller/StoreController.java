package com.palmen.supermarket.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.palmen.supermarket.dto.StoreDTO;
import com.palmen.supermarket.service.IStoreService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
public class StoreController {

	private final IStoreService storeService;

	@PostMapping("/createStore")
	public ResponseEntity<?> createStore(@RequestBody StoreDTO store) {
		Boolean storeCreated = storeService.createStore(store);

		return storeCreated ? ResponseEntity.status(HttpStatus.CREATED).body(storeCreated)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Store not created");
	}

	@DeleteMapping("/deleteStore/{id}")
	public ResponseEntity<?> deleteStore(@PathVariable Long id) {
		Boolean storeDeleted = storeService.deleteStore(id);

		return storeDeleted ? ResponseEntity.status(HttpStatus.OK).body(storeDeleted)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not deleted");
	}

	@GetMapping("/findStore/{id}")
	public ResponseEntity<?> findStore(@PathVariable Long id) {
		StoreDTO storeFound = storeService.findStore(id);

		return storeFound != null ? ResponseEntity.status(HttpStatus.FOUND).body(storeFound)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found");
	}

	@GetMapping("/findAllStores")
	public ResponseEntity<?> findAllStores() {
		List<StoreDTO> stores = storeService.findAllStores();

		return stores.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stores not found")
				: ResponseEntity.status(HttpStatus.FOUND).body(stores);
	}
}
