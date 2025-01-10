package com.palmen.supermarket.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palmen.supermarket.dto.BrandDTO;
import com.palmen.supermarket.mapper.IBrandMapper;
import com.palmen.supermarket.persistence.entity.Brand;
import com.palmen.supermarket.persistence.repository.IBrandRepository;
import com.palmen.supermarket.service.IBrandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements IBrandService {

	private final IBrandRepository brandRepository;
	private final IBrandMapper brandMapper;

	@Transactional
	@Override
	public Boolean createBrand(BrandDTO brandDTO) {
		Brand brand = brandMapper.brandDTOToBrand(brandDTO);
		return brandRepository.save(brand) != null;
	}
	
	@Transactional
	@Override
	public BrandDTO updateBrand(BrandDTO brandDTO) {
		Brand existingBrand = brandRepository.findById(brandDTO.getId())
				.orElseThrow();
		
		Brand updatedBrand = Brand.builder()
				.name(brandDTO.getName() != null ? brandDTO.getName() : existingBrand.getName())
				.build();
		
		brandRepository.save(updatedBrand);
		return brandMapper.brandToBrandDTO(updatedBrand);
	}

	@Transactional
	@Override
	public Boolean deleteBrand(Long id) {
		if(brandRepository.existsById(id)) {
			brandRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	@Override
	public BrandDTO findBrand(Long id) {
		return brandRepository.findById(id)
				.map(brandMapper::brandToBrandDTO)
				.orElseThrow();
	}

	@Transactional(readOnly = true)
	@Override
	public List<BrandDTO> findAllBrands() {
		return brandRepository.findAll()
				.stream()
				.map(brandMapper::brandToBrandDTO)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	@Override
	public BrandDTO findBrandByName(String name){
		return brandRepository.findByName(name)
				.map(brandMapper::brandToBrandDTO)
				.orElseThrow();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Long countProductsByBrand(Long id) {
		return brandRepository.countProductsByBrandId(id);
	}
	
}
