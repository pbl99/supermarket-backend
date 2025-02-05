package com.palmen.supermarket.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.palmen.supermarket.product.dto.BrandDTO;
import com.palmen.supermarket.product.mapper.IBrandMapper;
import com.palmen.supermarket.product.persistence.entity.Brand;
import com.palmen.supermarket.product.persistence.repository.IBrandRepository;
import com.palmen.supermarket.product.service.impl.BrandServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BrandServiceImplTest {

	private IBrandRepository brandRepository;
	private IBrandMapper brandMapper;
	private BrandServiceImpl brandServiceImpl;
	
	@BeforeEach
	void setup() {
		brandRepository = Mockito.mock(IBrandRepository.class);
		brandMapper = Mockito.mock(IBrandMapper.class);
		brandServiceImpl = new BrandServiceImpl(brandRepository, brandMapper);
	}
	
	@Test
	void createBrandSuccesfully_Test() {
		//Given
		
		BrandDTO brandDTO = BrandDTO.builder()
				.id((long)1)
				.name("Nike")
				.build();
		
		Brand brand = Brand.builder()
				.id((long)1)
				.name("Nike")
				.build();
		
		when(brandMapper.brandDTOToBrand(brandDTO)).thenReturn(brand);
		when(brandRepository.save(brand)).thenReturn(brand);
		
		//When 
		
		Boolean result = brandServiceImpl.createBrand(brandDTO);
		
		//Then 
		
		assertTrue(result);
		verify(brandMapper).brandDTOToBrand(brandDTO);
		verify(brandRepository).save(brand);
	}
	
	@Test
	void createBrandFail_Test() {
		BrandDTO brandDTO = BrandDTO.builder()
				.id((long)1)
				.name("Nike")
				.build();
		
		Brand brand = Brand.builder()
				.id((long)1)
				.name("Nike")
				.build();
		
		when(brandMapper.brandDTOToBrand(brandDTO)).thenReturn(brand);
		when(brandRepository.save(brand)).thenReturn(null);
		
		Boolean result = brandServiceImpl.createBrand(brandDTO);
		
		assertFalse(result);
		verify(brandMapper).brandDTOToBrand(brandDTO);
		verify(brandRepository).save(brand);
	}
	
	@Test
	void updateBrandSuccessfully_Test() {
		BrandDTO brandDTO = BrandDTO.builder()
				.id((long)1)
				.name("Nike")
				.build();
		
		Brand brand = Brand.builder()
				.name("Nike")
				.build();
		
		when(brandRepository.findById(brandDTO.getId())).thenReturn(Optional.of(brand));
		when(brandRepository.save(brand)).thenReturn(brand);
		when(brandMapper.brandToBrandDTO(brand)).thenReturn(brandDTO);
		
		BrandDTO brandDTOResult = brandServiceImpl.updateBrand(brandDTO);
		
		assertThat(brandDTOResult).isNotNull();
		verify(brandRepository).findById(brandDTO.getId());
		verify(brandRepository).save(brand);
		verify(brandMapper).brandToBrandDTO(brand);
	}
}
