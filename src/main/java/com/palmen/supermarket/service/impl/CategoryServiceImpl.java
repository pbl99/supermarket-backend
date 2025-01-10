package com.palmen.supermarket.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.palmen.supermarket.dto.CategoryDTO;
import com.palmen.supermarket.mapper.ICategoryMapper;
import com.palmen.supermarket.persistence.entity.Category;
import com.palmen.supermarket.persistence.repository.ICategoryRepository;
import com.palmen.supermarket.service.ICategoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

	private final ICategoryRepository categoryRepository;
	private final ICategoryMapper categoryMapper;

	@Transactional
	@Override
	public Boolean createCategory(CategoryDTO categoryDTO) {
		Category category = categoryMapper.categoryDTOToCategory(categoryDTO);
		return categoryRepository.save(category) != null;
	}

	@Transactional
	@Override
	public Boolean deleteCategory(Long id) {
		if (categoryRepository.existsById(id)) {
			categoryRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	@Override
	public CategoryDTO findCategory(Long id) {
		return categoryRepository.findById(id)
				.map(categoryMapper::categoryToCategoryDTO)
				.orElseThrow(() -> new RuntimeException("Product not found"));
	}

	@Transactional(readOnly = true)
	@Override
	public List<CategoryDTO> findAllCategories() {
		return categoryRepository.findAll()
				.stream()
				.map(categoryMapper::categoryToCategoryDTO)
				.collect(Collectors.toList());
	}

}
