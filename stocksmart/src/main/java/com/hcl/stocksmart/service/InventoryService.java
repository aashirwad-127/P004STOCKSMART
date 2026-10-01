package com.hcl.stocksmart.service;

import com.hcl.stocksmart.model.Inventory;
import com.hcl.stocksmart.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventory(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    // Low Stock
    public List<Inventory> getLowStock() {

        return inventoryRepository.findAll()
                .stream()
                .filter(inventory ->
                        inventory.getQuantity() <= inventory.getMinimumStock())
                .toList();
    }
}