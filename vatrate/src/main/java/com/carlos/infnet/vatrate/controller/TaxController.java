package com.carlos.infnet.vatrate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.infnet.vatrate.model.TransactionPayload;
import com.carlos.infnet.vatrate.service.TaxService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TaxController {
    private final TaxService taxService;

    @PostMapping
    public ResponseEntity<?> calcTax(@RequestBody TransactionPayload transactionPayload) {        
        BigDecimal impostoTotal = taxService.calcularTaxTotal(transactionPayload);
        return ResponseEntity.ok(Map.of("imposto", impostoTotal));
    }
    
}
