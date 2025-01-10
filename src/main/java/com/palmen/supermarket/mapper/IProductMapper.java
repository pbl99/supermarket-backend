package com.palmen.supermarket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import com.palmen.supermarket.dto.ProductDTO;
import com.palmen.supermarket.persistence.entity.Brand;
import com.palmen.supermarket.persistence.entity.Category;
import com.palmen.supermarket.persistence.entity.Product;

@Mapper(componentModel = "spring")
public interface IProductMapper {

	@Mapping(target = "brandName", source = "brand.name")
	@Mapping(target = "categoryName", source = "category.name")
	ProductDTO productToProductDTO(Product product);

	@Mapping(target = "brand", source = "brandName", qualifiedByName = "stringToBrand")
	@Mapping(target = "category", source = "categoryName", qualifiedByName = "stringToCategory")
	@Mapping(target = "stocks", ignore = true)
	Product productDTOToProduct(ProductDTO productDTO);


	@Named("stringToBrand")
	default Brand mapBrand(String name) {
		if (name == null)
			return null;
		return Brand.builder().name(name).build();
	}

	@Named("stringToCategory")
	default Category mapCategory(String name) {
		if (name == null)
			return null;
		return Category.builder().name(name).build();
	}
}