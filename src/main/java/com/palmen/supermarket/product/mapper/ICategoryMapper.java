package com.palmen.supermarket.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.palmen.supermarket.product.dto.CategoryDTO;
import com.palmen.supermarket.product.persistence.entity.Category;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {

	@Mapping(target = "products", ignore = true)
	Category categoryDTOToCategory(CategoryDTO categoryDTO);

	CategoryDTO categoryToCategoryDTO(Category category);
}
