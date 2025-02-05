package com.palmen.supermarket.product.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.palmen.supermarket.product.persistence.entity.Brand;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, Long> {

	Optional<Brand> findByName(String name);
	
    @Query("SELECT COUNT(p) FROM Product p WHERE p.brand.id = :brandId")
	Long countProductsByBrandId(@Param("brandId") Long brandId);
}
