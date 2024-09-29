package com.carlos.infnet.product_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.infnet.product_service.model.Category;
import com.carlos.infnet.product_service.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Tag(name = "Category API", description = "API para gerenciar categorias")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/category")
    @Operation(summary = "Criar um nova categoria", description = "Cria um nova categoria e retorna o produto criado")
    @ApiResponse(responseCode = "201", description = "categoria criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Solicitação inválida")
    public Category create(
        @RequestBody @Parameter(description = "Categoria a ser criado") Category category) {
            log.info("Creating category: {}", category);
        return categoryService.create(category);
    }

    @GetMapping("/category")
    @Operation(summary = "Listar todas as categorias", description = "Retorna uma lista de todas as categorias disponíveis")
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/category/{id}")
    @Operation(summary = "Encontrar uma categoria por ID", description = "Retorna a categoria com o ID fornecido")
    @ApiResponse(responseCode = "200", description = "Categoria encontrado")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrado")
    public Optional<Category> findById(@PathVariable @Parameter(description = "ID da Categoria") Long id) {
        return categoryService.findById(id);
    }


}
