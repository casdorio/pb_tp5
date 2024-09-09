package com.carlos.infnet.product_service.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carlos.infnet.product_service.model.Product;
import com.carlos.infnet.product_service.repository.ProductRepository;
import com.carlos.infnet.product_service.service.ProductService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProducServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        if(!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto não encontrado " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product productAlterado) {
        return productRepository.findById(id).map(product -> {
            product.setName(productAlterado.getName());
            product.setDescription(productAlterado.getDescription());
            product.setCountry(productAlterado.getCountry());
            product.setPrice(productAlterado.getPrice());
            product.setIdCategory(productAlterado.getIdCategory());
            product.setStock(productAlterado.getStock());
            return productRepository.save(product);
        }).orElseGet(() -> {
            productAlterado.setId(id);
            return productRepository.save(productAlterado);
        });
    }

}
