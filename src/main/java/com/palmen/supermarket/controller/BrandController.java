package com.palmen.supermarket.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.palmen.supermarket.dto.BrandDTO;
import com.palmen.supermarket.service.IBrandService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brand")
public class BrandController {

	private final IBrandService brandService;

	@PostMapping("/createBrand")
	public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO) {
		boolean result = brandService.createBrand(brandDTO);

		if (result) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Brand created successfully");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Brand creation failed");
		}
	}

	@PutMapping("/updateBrand")
	public ResponseEntity<?> updateBrand(@RequestBody BrandDTO brandDTO) {
		BrandDTO brandDTOResult = brandService.updateBrand(brandDTO);

		if (brandDTOResult != null) {
			return ResponseEntity.ok(brandDTOResult);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Brand updated failed");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBrand(@PathVariable Long id) {
		Boolean result = brandService.deleteBrand(id);

		if (result) {
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
		}
	}

	@GetMapping("/findBrand/{id}")
	public ResponseEntity<?> findBrand(@PathVariable Long id) {
		BrandDTO brandDTO = brandService.findBrand(id);

		if (brandDTO != null) {
			return ResponseEntity.ok(brandDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brand wasn't found");
		}
	}

	@GetMapping("/findAllBrands")
	public ResponseEntity<?> findAllBrands() {
		List<BrandDTO> brands = brandService.findAllBrands();

		if (brands == null || brands.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(brands);
		}
	}

	@GetMapping("/findBrandByName/{brandName}")
	public ResponseEntity<?> findBrandByName(@PathVariable String brandName) {
		BrandDTO brandDTO = brandService.findBrandByName(brandName);

		if (brandDTO != null) {
			return ResponseEntity.ok(brandDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brand wasn't found");
		}
	}

	@GetMapping("/countProductsByBrand/{id}")
	public ResponseEntity<?> countProductsByBrand(@PathVariable Long id) {
		Long products = brandService.countProductsByBrand(id);

		if (products != null) {
			return ResponseEntity.ok(products);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brand not found");
		}
	}

}