package com.carlos.infnet.venda_service.service;

import org.springframework.stereotype.Service;

import com.carlos.infnet.venda_service.model.Product;
import com.carlos.infnet.venda_service.service.feign.ProductClient;

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
