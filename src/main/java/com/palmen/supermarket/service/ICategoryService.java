package com.palmen.supermarket.service;

import java.util.List;

import com.palmen.supermarket.dto.CategoryDTO;

public interface ICategoryService {

	Boolean createCategory(CategoryDTO categoryDTO);

	Boolean deleteCategory(Long id);

	CategoryDTO findCategory(Long id);

	List<CategoryDTO> findAllCategories();
}
