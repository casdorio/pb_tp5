package com.carlos.infnet.venda_service.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.carlos.infnet.venda_service.model.Product;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {
    
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable Long id);
}
