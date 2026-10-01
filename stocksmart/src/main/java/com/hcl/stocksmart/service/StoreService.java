package com.hcl.stocksmart.service;

import com.hcl.stocksmart.model.Store;
import com.hcl.stocksmart.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    public Store addStore(Store store) {
        return storeRepository.save(store);
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStore(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }
}
