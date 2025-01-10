package com.palmen.supermarket.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.palmen.supermarket.dto.BrandDTO;
import com.palmen.supermarket.service.IBrandService;

@ExtendWith(MockitoExtension.class)
public class BrandControllerTest {

	private IBrandService brandService;
	private BrandController brandController;

	@BeforeEach
	void setup() {
		brandService = Mockito.mock(IBrandService.class);
		brandController = new BrandController(brandService);
	}

	@Test
	void createBrandSuccessfully_Test() {
		BrandDTO brandDTO = BrandDTO.builder()
				.id((long)1)
				.name("Nike")
				.build();
		
		when(brandService.createBrand(brandDTO)).thenReturn(true);

		ResponseEntity<?> result = brandController.createBrand(brandDTO);
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(result.getBody()).isEqualTo("Brand created successfully");
		verify(brandService).createBrand(brandDTO);
	}
	
	@Test
	void createBrandFail_Test() {
		BrandDTO brandDTO = BrandDTO.builder()
				.id((long)1)
				.name(null)
				.build();
		
		when(brandService.createBrand(brandDTO)).thenReturn(false);
		
		ResponseEntity<?> result = brandController.createBrand(brandDTO);
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
		assertThat(result.getBody()).isEqualTo("Brand creation failed");
		verify(brandService).createBrand(brandDTO);
	}
	
	@Test
	void updateBrandSuccessfully_Test() {
		BrandDTO brandDTO = BrandDTO.builder()
				.id((long)1)
				.name("Nike")
				.build();
		
		when(brandService.updateBrand(brandDTO)).thenReturn(brandDTO);
		
		ResponseEntity<?> result = brandController.updateBrand(brandDTO);
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		verify(brandService).updateBrand(brandDTO);
	}
	
	@Test
	void updateBrandFail_Test() {
		BrandDTO brandDTO = BrandDTO.builder()
				.id((long)1)
				.name(null)
				.build();
		
		when(brandService.updateBrand(brandDTO)).thenReturn(null);
		
		ResponseEntity<?> result = brandController.updateBrand(brandDTO);
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
		assertThat(result.getBody()).isEqualTo("Brand updated failed");
		verify(brandService).updateBrand(brandDTO);
	}
	
	@Test
	void deleteBrandSuccessfully_Test() {
		Long id = (long) 1;
		
		when(brandService.deleteBrand(id)).thenReturn(true);
		
		ResponseEntity<?> result = brandController.deleteBrand(id);
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertEquals(true, result.getBody());
		verify(brandService).deleteBrand(id);
	}
	
	@Test
	void deleteBrandFail_Test() {
		Long id = (long) 1;
		
		when(brandService.deleteBrand(id)).thenReturn(false);
		
		ResponseEntity<?> result = brandController.deleteBrand(id);
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
		assertEquals(false, result.getBody());
		verify(brandService).deleteBrand(id);
	}
	
	@Test
	void findBrandSuccesfully_Test() {
		Long id = (long) 1;
		
		BrandDTO brandDTO = BrandDTO.builder()
				.id((long)1)
				.name("Nike")
				.build();
		
		when(brandService.findBrand(id)).thenReturn(brandDTO);
		
		ResponseEntity<?> result = brandController.findBrand(id);
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).isEqualTo(brandDTO);
		verify(brandService).findBrand(id);
	}
	
	@Test
	void findBrandFail_Test() {
		Long id = (long) 1;
		
		when(brandService.findBrand(id)).thenReturn(null);
		
		ResponseEntity<?> result = brandController.findBrand(id);
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(result.getBody()).isEqualTo("Brand wasn't found");
		verify(brandService).findBrand(id);
	}
	
	@Test
	void findAllBrandsSuccessfully_Test() {
		BrandDTO brand1 = BrandDTO.builder()
				.id((long)1)
				.name("Nike")
				.build();
		
		BrandDTO brand2 = BrandDTO.builder()
				.id((long)2)
				.name("Adidas")
				.build();
		
		List<BrandDTO> brands = List.of(brand1, brand2);
		
		when(brandService.findAllBrands()).thenReturn(brands);
		
		ResponseEntity<?> result = brandController.findAllBrands();
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody().equals(brands));
		verify(brandService).findAllBrands();
	}
	
	@Test
	void findAllBrandsFail_Test() {
		when(brandService.findAllBrands()).thenReturn(null);
		
		ResponseEntity<?> result = brandController.findAllBrands();
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		verify(brandService).findAllBrands();
	}
	
	@Test
	void findAllBrandsFailEmpty_Test() {
		when(brandService.findAllBrands()).thenReturn(Collections.emptyList());
		
		ResponseEntity<?> result = brandController.findAllBrands();
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		verify(brandService).findAllBrands();
	}
	
	@Test
	void findBrandByNameSuccessfully_Test() {
		BrandDTO brandDTO = BrandDTO.builder()
				.id((long)1)
				.name("Nike")
				.build();
		
		String brandName = "Nike";
		
		when(brandService.findBrandByName(brandName)).thenReturn(brandDTO);
		
		ResponseEntity<?> result = brandController.findBrandByName(brandName);
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody().equals(brandDTO));
		verify(brandService).findBrandByName(brandName);
	}
	
	@Test
	void findBrandByNameFail_Test() {
		String brandName = "Nike";
		
		when(brandService.findBrandByName(brandName)).thenReturn(null);
		
		ResponseEntity<?> result = brandController.findBrandByName(brandName);
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(result.getBody()).isEqualTo("Brand wasn't found");
		verify(brandService).findBrandByName(brandName);
	}
	
	@Test
	void countProductsByBrandSuccesfully_Test() {
		Long id = (long) 1;
		
		Long products = (long) 4;
		
		when(brandService.countProductsByBrand(id)).thenReturn(products);
		
		ResponseEntity<?> result = brandController.countProductsByBrand(id);
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).isEqualTo(products);
	}
	
	@Test
	void countProductsByBrandFail_Test() {
		Long id = (long) 1;
		
		when(brandService.countProductsByBrand(id)).thenReturn(null);
		
		ResponseEntity<?> result = brandController.countProductsByBrand(id);
		
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(result.getBody()).isEqualTo("Brand not found");
	}
}
