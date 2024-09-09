package com.carlos.infnet.product_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carlos.infnet.product_service.model.Product;
import com.carlos.infnet.product_service.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.*;

@RestController
@RequestMapping("/")
@Tag(name = "Product API", description = "API para gerenciar produtos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto e retorna o produto criado")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Solicitação inválida")
    public ResponseEntity<?> create(
        @RequestBody @Parameter(description = "Produto a ser criado com categoria (category_id)") Product product) {
            Product saved = productService.create(product);
            return ResponseEntity.ok(saved);
    }

    @GetMapping("/product")
    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista de todos os produtos")
    @ApiResponse(responseCode = "200", description = "Lista de produtos")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/product/{id}")
    @Operation(summary = "Encontrar um produto por ID", description = "Retorna o produto com o ID fornecido")
    @ApiResponse(responseCode = "200", description = "Produto encontrado")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    public Optional<Product> findById(@PathVariable @Parameter(description = "ID do produto") Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/product/{id}")
    @Operation(summary = "Excluir um produto por ID", description = "Exclui o produto com o ID fornecido")
    @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    public void delete(@PathVariable @Parameter(description = "ID do produto") Long id) {
        productService.delete(id);
    }

    @PutMapping("/product/{id}")
    @Operation(summary = "Atualizar um produto", description = "Atualiza o produto com o ID fornecido e retorna o produto atualizado")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Solicitação inválida")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    public Product update(
        @PathVariable @Parameter(description = "ID do produto") Long id, 
        @RequestBody @Parameter(description = "Produto com as informações atualizadas e categoria (category_id)") Product product) {
        return productService.update(id, product);
    }
}
