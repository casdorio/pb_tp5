package com.carlos.infnet.product_service.service;

import java.util.List;
import java.util.Optional;

import com.carlos.infnet.product_service.model.Category;

public interface CategoryService {

    Category create(Category category);

    List<Category> findAll();

    Optional<Category> findById(Long id);

}
