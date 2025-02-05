package com.palmen.supermarket.store.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.palmen.supermarket.store.dto.StoreDTO;
import com.palmen.supermarket.store.mapper.IStoreMapper;
import com.palmen.supermarket.store.persistence.entity.Store;
import com.palmen.supermarket.store.persistence.repository.IStoreRepository;
import com.palmen.supermarket.store.service.IStoreService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements IStoreService {

	private final IStoreRepository storeRepository;
	private final IStoreMapper storeMapper;

	@Transactional
	@Override
	public Boolean createStore(StoreDTO storeDTO) {
		Store store = storeMapper.storeDTOToStore(storeDTO);
		return storeRepository.save(store) != null;
	}

	@Transactional
	@Override
	public Boolean deleteStore(Long id) {
		if (storeRepository.existsById(id)) {
			storeRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Transactional(readOnly = true)
	@Override
	public StoreDTO findStore(Long id) {
		return storeRepository.findById(id)
				.map(storeMapper::storeToStoreDTO)
				.orElseThrow();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<StoreDTO> findAllStores(){
		return storeRepository.findAll()
				.stream()
				.map(storeMapper::storeToStoreDTO)
				.collect(Collectors.toList());
	}
}
