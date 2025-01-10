package com.palmen.supermarket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.palmen.supermarket.dto.BrandDTO;
import com.palmen.supermarket.persistence.entity.Brand;

@Mapper(componentModel = "spring")
public interface IBrandMapper {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "products", ignore = true)
	Brand brandDTOToBrand(BrandDTO brandDTO);

	BrandDTO brandToBrandDTO(Brand brand);

}
