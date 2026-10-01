package com.hcl.stocksmart.repository;

import com.hcl.stocksmart.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}