package com.hcl.stocksmart.repository;

import com.hcl.stocksmart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
