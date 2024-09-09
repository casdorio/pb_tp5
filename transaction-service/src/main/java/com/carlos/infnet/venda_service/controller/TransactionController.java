package com.carlos.infnet.venda_service.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carlos.infnet.venda_service.model.Transaction;
import com.carlos.infnet.venda_service.service.TaxService;

import com.carlos.infnet.venda_service.service.TransactionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/transaction")
@Slf4j
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final TaxService taxService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Transaction transaction) {
        BigDecimal totalImposto = taxService.getTaxByItems(transaction).totalImposto();
        BigDecimal valorSemImposto = transactionService.calcularValorTotal(transaction);
        transaction.setTotalTaxcost(totalImposto);
        transaction.setTotalCost(valorSemImposto);
        transaction.setTotalCostEndTax(valorSemImposto.add(totalImposto));
        Transaction saved = transactionService.create(transaction);
        return ResponseEntity.ok(Map.of("transaction", saved));
    }
}
