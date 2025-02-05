package com.palmen.supermarket.store.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.palmen.supermarket.store.persistence.entity.Store;


@Repository
public interface IStoreRepository extends JpaRepository<Store, Long> {

}
