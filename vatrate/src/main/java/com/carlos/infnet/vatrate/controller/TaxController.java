package com.carlos.infnet.vatrate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.infnet.vatrate.model.TransactionPayload;
import com.carlos.infnet.vatrate.service.TaxService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class TaxController {
    private final TaxService taxService;

    @PostMapping
    public ResponseEntity<?> calcTax(@RequestBody TransactionPayload transactionPayload) {    
        BigDecimal impostoTotal = taxService.calcularTaxTotal(transactionPayload);
        return ResponseEntity.ok(Map.of("imposto", impostoTotal));
    }
    
}
