package com.carlos.infnet.vatrate.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.carlos.infnet.vatrate.model.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaxService {

    private final ProductService productService;

    public BigDecimal calcularTaxTotal(TransactionPayload transactionPayload){
        return transactionPayload.items().stream()
                .map(this::calcularTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private BigDecimal calcularTax(ItemTransaction itemTransaction){
        Product product = productService.getProductById(itemTransaction.getProductId());
        String countryString = product.getCountry().toUpperCase();
        Country country = Country.valueOf(countryString);
        BigDecimal taxa = getTax(country);
        return product.getPrice()
            .multiply(taxa)
            .multiply(new BigDecimal(itemTransaction.getQuantity()));

    }

    private BigDecimal getTax(Country country) {
        return switch (country) {
            case BRASIL -> new BigDecimal("0.1");
            case ARGENTINA -> new BigDecimal("0.2");
            case URUGUAI -> new BigDecimal("0.3");
            case PARAGUAI -> new BigDecimal("0.4");
            case CHILE -> new BigDecimal("0.5");
            case BOLIVIA -> new BigDecimal("0.6");
            case PERU -> new BigDecimal("0.7");
            case EQUADOR -> new BigDecimal("0.8");
            case COLOMBIA -> new BigDecimal("0.9");
            case VENEZUELA -> new BigDecimal("1.0");
            case GUIANA -> new BigDecimal("1.1");
            case SURINAME -> new BigDecimal("1.2");
            case GUIANA_FRANCESA -> new BigDecimal("1.3");
        };
    }
}
