package com.hcl.stocksmart.controller;

import com.hcl.stocksmart.model.Store;
import com.hcl.stocksmart.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    StoreService storeService;

    @PostMapping
    public Store addStore(@RequestBody Store store) {
        return storeService.addStore(store);
    }

    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public Store getStore(@PathVariable Long id) {
        return storeService.getStore(id);
    }

    @DeleteMapping("/{id}")
    public String deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return "Store deleted successfully";
    }
}
