package com.hcl.stocksmart.service;

import com.hcl.stocksmart.model.Inventory;
import com.hcl.stocksmart.model.Order;
import com.hcl.stocksmart.model.OrderItem;
import com.hcl.stocksmart.model.Product;

import com.hcl.stocksmart.repository.InventoryRepository;
import com.hcl.stocksmart.repository.OrderItemRepository;
import com.hcl.stocksmart.repository.OrderRepository;
import com.hcl.stocksmart.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;


    public OrderItem addOrderItem(OrderItem orderItem) {

        // Get Order ID from request
        Long orderId = orderItem.getOrder().getId();

        // Load complete Order from database
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        // Check Store exists in Order
        if (order.getStore() == null) {
            throw new RuntimeException(
                    "Store is not assigned to this order"
            );
        }

        // Get Store ID from complete Order
        Long storeId = order.getStore().getId();


        // Get Product ID from request
        Long productId = orderItem.getProduct().getId();

        // Load complete Product from database
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));


        // Find inventory for this Product + Store
        Inventory inventory =
                inventoryRepository.findByProductIdAndStoreId(
                        productId,
                        storeId
                );

        if (inventory == null) {
            throw new RuntimeException(
                    "Inventory not found for this product and store"
            );
        }


        // Check stock
        if (inventory.getQuantity() < orderItem.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }


        // Reduce stock
        int newQuantity =
                inventory.getQuantity() - orderItem.getQuantity();

        inventory.setQuantity(newQuantity);

        inventoryRepository.save(inventory);


        // Attach actual database objects
        orderItem.setOrder(order);
        orderItem.setProduct(product);


        // Save OrderItem
        return orderItemRepository.save(orderItem);
    }


    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }


    public OrderItem getOrderItem(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }


    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}