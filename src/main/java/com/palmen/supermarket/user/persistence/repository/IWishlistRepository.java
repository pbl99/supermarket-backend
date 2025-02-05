package com.palmen.supermarket.user.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.palmen.supermarket.user.persistence.entity.Wishlist;

@Repository
public interface IWishlistRepository extends JpaRepository<Wishlist, Long>{

}
