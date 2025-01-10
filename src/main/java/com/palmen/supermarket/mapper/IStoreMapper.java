package com.palmen.supermarket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.palmen.supermarket.dto.StoreDTO;
import com.palmen.supermarket.persistence.entity.Store;

@Mapper(componentModel = "spring")
public interface IStoreMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "stocks", ignore = true)
	Store storeDTOToStore(StoreDTO storeDTO);

	StoreDTO storeToStoreDTO(Store store);
}
