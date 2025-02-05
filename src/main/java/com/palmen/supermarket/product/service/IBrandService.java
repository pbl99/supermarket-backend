package com.palmen.supermarket.product.service;

import java.util.List;

import com.palmen.supermarket.product.dto.BrandDTO;

public interface IBrandService {

	Boolean createBrand(BrandDTO brandDTO);

	Boolean deleteBrand(Long id);

	BrandDTO findBrand(Long id);

	List<BrandDTO> findAllBrands();

	BrandDTO updateBrand(BrandDTO brandDTO);

	BrandDTO findBrandByName(String name);

	Long countProductsByBrand(Long id);
}
