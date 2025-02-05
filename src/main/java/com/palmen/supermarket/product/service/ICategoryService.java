package com.palmen.supermarket.product.service;

import java.util.List;

import com.palmen.supermarket.product.dto.CategoryDTO;

public interface ICategoryService {

	Boolean createCategory(CategoryDTO categoryDTO);

	Boolean deleteCategory(Long id);

	CategoryDTO findCategory(Long id);

	List<CategoryDTO> findAllCategories();
}
