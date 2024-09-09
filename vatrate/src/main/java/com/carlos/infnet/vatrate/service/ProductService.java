package com.carlos.infnet.vatrate.service;

import org.springframework.stereotype.Service;

import com.carlos.infnet.vatrate.model.Product;
import com.carlos.infnet.vatrate.service.feign.ProductClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductClient productClient;
    
    public Product getProductById(Long id) {            
        Product product = productClient.getProductById(id);
        return product;
    }
}
