package com.palmen.supermarket.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.palmen.supermarket.product.dto.ProductDTO;
import com.palmen.supermarket.product.exception.BrandNotFoundException;
import com.palmen.supermarket.product.exception.CategoryNotFoundException;
import com.palmen.supermarket.product.mapper.IProductMapper;
import com.palmen.supermarket.product.persistence.entity.Brand;
import com.palmen.supermarket.product.persistence.entity.Category;
import com.palmen.supermarket.product.persistence.entity.Product;
import com.palmen.supermarket.product.persistence.repository.IBrandRepository;
import com.palmen.supermarket.product.persistence.repository.ICategoryRepository;
import com.palmen.supermarket.product.persistence.repository.IProductRepository;
import com.palmen.supermarket.product.service.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

	private final IProductRepository productRepository;
	private final IBrandRepository brandRepository;
	private final ICategoryRepository categoryRepository;
	private final IProductMapper productMapper;

	@Transactional
	@Override
	public Boolean createProduct(ProductDTO productDTO) {
		Brand brand = brandRepository.findByName(productDTO.getBrandName())
				.orElseThrow(() -> new BrandNotFoundException("Brand not found: " + productDTO.getBrandName()));

		Category category = categoryRepository.findByName(productDTO.getCategoryName())
				.orElseThrow(() -> new CategoryNotFoundException("Category not found: " + productDTO.getCategoryName()));

		Product product = productMapper.productDTOToProduct(productDTO);

		product.setBrand(brand);
		product.setCategory(category);

		Product savedProduct = productRepository.save(product);

		return savedProduct.getId() != null;
	}
	
	@Transactional
	@Override
	public ProductDTO updateProduct(ProductDTO productDTO) {
	    Product existingProduct = productRepository.findById(productDTO.getId())
	            .orElseThrow(() -> new RuntimeException("Product not found"));

	    Product updatedProduct = Product.builder()
	            .id(existingProduct.getId())
	            .name(productDTO.getName() != null ? productDTO.getName() : existingProduct.getName())
	            .description(productDTO.getDescription() != null ? productDTO.getDescription() : existingProduct.getDescription())
	            .weight(productDTO.getWeight() != null ? productDTO.getWeight() : existingProduct.getWeight())
	            .basePrice(productDTO.getBasePrice() != null ? productDTO.getBasePrice() : existingProduct.getBasePrice())
	            .expirationDate(productDTO.getExpirationDate() != null ? productDTO.getExpirationDate() : existingProduct.getExpirationDate())
	            .productType(productDTO.getProductType() != null ? productDTO.getProductType() : existingProduct.getProductType())
	            .imageUrl(productDTO.getImageUrl() != null ? productDTO.getImageUrl() : existingProduct.getImageUrl())
	            .category(existingProduct.getCategory()) 
	            .brand(existingProduct.getBrand())
	            .stocks(existingProduct.getStocks())
	            .build();

	    productRepository.save(updatedProduct);
	    return productMapper.productToProductDTO(updatedProduct);
	}

	@Transactional
	@Override
	public Boolean deleteProduct(Long id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	@Override
	public ProductDTO findProduct(Long id) {
		return productRepository.findById(id).map(productMapper::productToProductDTO)
				.orElseThrow(() -> new RuntimeException("Product not found"));
	}

	@Transactional(readOnly = true)
	@Override
	public List<ProductDTO> findAllProducts() {
		return productRepository.findAll().stream()
				.map(productMapper::productToProductDTO)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<ProductDTO> findAllProductsByBrand(String brandName){
		return productRepository.findByBrand_Name(brandName).stream()
				.map(productMapper::productToProductDTO)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<ProductDTO> findAllProductsByCategory(String category){
		return productRepository.findByCategory_Name(category).stream()
				.map(productMapper::productToProductDTO)
				.collect(Collectors.toList());
	}
	
	/*@Transactional(readOnly = true)
	@Override
	public List<ProductDTO> findAllProductsByFilters(ProductFilterDTO productFilterDTO){
		
	}*/
}
