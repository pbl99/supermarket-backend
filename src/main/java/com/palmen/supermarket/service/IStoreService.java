package com.palmen.supermarket.service;

import java.util.List;

import com.palmen.supermarket.dto.StoreDTO;

public interface IStoreService {

	Boolean createStore(StoreDTO storeDTO);

	Boolean deleteStore(Long id);

	StoreDTO findStore(Long id);

	List<StoreDTO> findAllStores();
}
