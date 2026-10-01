package com.hcl.stocksmart.repository;

import com.hcl.stocksmart.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}