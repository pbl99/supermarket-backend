package com.palmen.supermarket.order.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.palmen.supermarket.order.persistence.entity.Purchase;

@Repository
public interface IPurchaseRepository extends JpaRepository<Purchase, Long>{
	List<Purchase> findAllByCustomer(Long id);
}
