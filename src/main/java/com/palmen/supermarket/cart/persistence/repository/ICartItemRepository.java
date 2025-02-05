package com.palmen.supermarket.cart.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.palmen.supermarket.cart.persistence.entity.CartItem;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItem, Long>{

}
