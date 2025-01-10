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
import com.palmen.supermarket.dto.ProductDTO;
import com.palmen.supermarket.service.IProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

	private final IProductService productService;

	@PostMapping("/createProduct")
	public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
		Boolean productCreated = productService.createProduct(productDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(productCreated + ": " + "Product created successfully");
	}

	@PutMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
		ProductDTO productUpdated = productService.updateProduct(productDTO);

		return productUpdated != null ? ResponseEntity.status(HttpStatus.OK).body(productUpdated)
				: ResponseEntity.status(HttpStatus.CONFLICT).body("Product not updated");
	}

	@DeleteMapping("/deleteMapping/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		Boolean productDeleted = productService.deleteProduct(id);

		return productDeleted ? ResponseEntity.status(HttpStatus.OK).body(productDeleted)
				: ResponseEntity.status(HttpStatus.CONFLICT).body("Product not deleted");
	}

	@GetMapping("/findProduct/{id}")
	public ResponseEntity<?> findProduct(@PathVariable Long id) {
		ProductDTO productFound = productService.findProduct(id);

		return productFound != null ? ResponseEntity.status(HttpStatus.FOUND).body(productFound)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
	}

	@GetMapping("/findAllProducts")
	public ResponseEntity<?> findAllProducts() {
		List<ProductDTO> products = productService.findAllProducts();

		return products.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Products not found")
				: ResponseEntity.status(HttpStatus.FOUND).body(products);
	}

	@GetMapping("/findAllProductsByBrand/{brandName}")
	public ResponseEntity<?> findAllProductsByBrand(@PathVariable String brandName) {
		List<ProductDTO> products = productService.findAllProductsByBrand(brandName);

		return products.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Products not found")
				: ResponseEntity.status(HttpStatus.FOUND).body(products);
	}

	@GetMapping("/findAllProductsByCategory/{category}")
	public ResponseEntity<?> findAllProductsByCategory(@PathVariable String category) {
		List<ProductDTO> products = productService.findAllProductsByBrand(category);

		return products.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Products not found")
				: ResponseEntity.status(HttpStatus.FOUND).body(products);
	}
}
