package com.hcl.stocksmart.repository;

import com.hcl.stocksmart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}