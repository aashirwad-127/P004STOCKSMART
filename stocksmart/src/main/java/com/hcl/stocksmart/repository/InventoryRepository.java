package com.hcl.stocksmart.repository;

import com.hcl.stocksmart.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProductIdAndStoreId(Long productId, Long storeId);
}
