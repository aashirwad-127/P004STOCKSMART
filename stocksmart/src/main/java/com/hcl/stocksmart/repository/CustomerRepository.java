package com.hcl.stocksmart.repository;

import com.hcl.stocksmart.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
