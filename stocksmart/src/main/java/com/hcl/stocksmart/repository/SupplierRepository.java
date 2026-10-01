package com.hcl.stocksmart.repository;

import com.hcl.stocksmart.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
