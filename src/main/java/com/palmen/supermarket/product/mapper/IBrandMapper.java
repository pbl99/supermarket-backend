package com.palmen.supermarket.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.palmen.supermarket.product.dto.BrandDTO;
import com.palmen.supermarket.product.persistence.entity.Brand;

@Mapper(componentModel = "spring")
public interface IBrandMapper {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "products", ignore = true)
	Brand brandDTOToBrand(BrandDTO brandDTO);

	BrandDTO brandToBrandDTO(Brand brand);

}
