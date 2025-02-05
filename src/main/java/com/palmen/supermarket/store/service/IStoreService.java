package com.palmen.supermarket.store.service;

import java.util.List;

import com.palmen.supermarket.store.dto.StoreDTO;

public interface IStoreService {

	Boolean createStore(StoreDTO storeDTO);

	Boolean deleteStore(Long id);

	StoreDTO findStore(Long id);

	List<StoreDTO> findAllStores();
}
