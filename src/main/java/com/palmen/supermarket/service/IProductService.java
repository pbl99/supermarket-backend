package com.palmen.supermarket.service;

import java.util.List;

import com.palmen.supermarket.dto.ProductDTO;

public interface IProductService {

	Boolean createProduct(ProductDTO productDTO);

	Boolean deleteProduct(Long id);

	ProductDTO findProduct(Long id);

	List<ProductDTO> findAllProducts();

	ProductDTO updateProduct(ProductDTO productDTO);

	List<ProductDTO> findAllProductsByBrand(String brandName);

	List<ProductDTO> findAllProductsByCategory(String category);

}
