package com.hcl.stocksmart.controller;

import com.hcl.stocksmart.model.Inventory;
import com.hcl.stocksmart.repository.ProductRepository;
import com.hcl.stocksmart.repository.OrderRepository;
import com.hcl.stocksmart.repository.CustomerRepository;
import com.hcl.stocksmart.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @GetMapping
    public Map<String, Long> getDashboard() {

        long totalProducts = productRepository.count();
        long totalOrders = orderRepository.count();
        long totalCustomers = customerRepository.count();

        long lowStockCount = inventoryRepository.findAll()
                .stream()
                .filter(inventory ->
                        inventory.getQuantity() <= inventory.getMinimumStock())
                .count();

        Map<String, Long> dashboard = new HashMap<>();

        dashboard.put("totalProducts", totalProducts);
        dashboard.put("totalOrders", totalOrders);
        dashboard.put("totalCustomers", totalCustomers);
        dashboard.put("lowStockCount", lowStockCount);

        return dashboard;
    }
}