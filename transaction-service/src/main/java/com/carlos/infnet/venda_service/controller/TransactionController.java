package com.carlos.infnet.venda_service.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.infnet.venda_service.model.Product;
import com.carlos.infnet.venda_service.model.Transaction;
import com.carlos.infnet.venda_service.service.NotificationService;
import com.carlos.infnet.venda_service.service.ProductService;
import com.carlos.infnet.venda_service.service.TaxService;

import com.carlos.infnet.venda_service.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;
    private final NotificationService notificationService;
    private final ProductService productService;
    private final TaxService taxService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Transaction transaction) {

        transaction.getItems().forEach(item -> {
            Product product = productService.getProductById(item.getProductId());
            item.setCountry(product.getCountry());
            item.setPrice(product.getPrice());
        });
        
        BigDecimal totalImposto = taxService.getTaxByItems(transaction).imposto();
        BigDecimal valorSemImposto = transactionService.calcularValorTotal(transaction);
        Transaction newTransaction = Transaction.builder()
            .userId(transaction.getUserId())
            .items(transaction.getItems())
            .totalTaxcost(totalImposto)
            .totalCost(valorSemImposto)
            .totalCostEndTax(valorSemImposto.add(totalImposto))
            .statusNotification("NOVA")
            .build();
        
        newTransaction.addStatusChange(newTransaction.getStatusNotification());
        Transaction saved = transactionService.save(newTransaction);
        try {
            notificationService.send(saved);
            saved.setStatusNotification("PROCESSANDO");
            saved.addStatusChange(saved.getStatusNotification());
            transactionService.save(saved);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error sending transaction", e);
        }

        return ResponseEntity.ok(Map.of("transaction", saved));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransactionById(@PathVariable String transactionId) {
        return ResponseEntity.ok(transactionService.getTransactionById(transactionId));
    }
    
}
