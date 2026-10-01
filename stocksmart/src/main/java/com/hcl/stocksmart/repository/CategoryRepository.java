package com.hcl.stocksmart.repository;

import com.hcl.stocksmart.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}