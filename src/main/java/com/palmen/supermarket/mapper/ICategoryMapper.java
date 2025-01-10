package com.palmen.supermarket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.palmen.supermarket.dto.CategoryDTO;
import com.palmen.supermarket.persistence.entity.Category;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {

	@Mapping(target = "products", ignore = true)
	Category categoryDTOToCategory(CategoryDTO categoryDTO);

	CategoryDTO categoryToCategoryDTO(Category category);
}
