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

import com.palmen.supermarket.dto.CategoryDTO;
import com.palmen.supermarket.service.ICategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

	private final ICategoryService categoryService;

	@PostMapping("/createCategory")
	public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
		Boolean categoryCreated = categoryService.createCategory(categoryDTO);

		return categoryCreated ? ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO)
				: ResponseEntity.status(HttpStatus.CONFLICT).body("Category not created");
	}

	@DeleteMapping("/deleteCategory/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		Boolean categoryDeleted = categoryService.deleteCategory(id);

		return categoryDeleted ? ResponseEntity.status(HttpStatus.OK).body(categoryDeleted)
				: ResponseEntity.status(HttpStatus.CONFLICT).body("Category not deleted");
	}

	@GetMapping("/findCategory/{id}")
	public ResponseEntity<?> findCategory(@PathVariable Long id) {
		CategoryDTO categoryDTO = categoryService.findCategory(id);

		return categoryDTO != null ? ResponseEntity.status(HttpStatus.OK).body(categoryDTO)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
	}

	@GetMapping("/findAllCategories")
	public ResponseEntity<?> findAllCategories() {
		List<CategoryDTO> categories = categoryService.findAllCategories();

		return categories.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
				: ResponseEntity.ok(categories);
	}

}
