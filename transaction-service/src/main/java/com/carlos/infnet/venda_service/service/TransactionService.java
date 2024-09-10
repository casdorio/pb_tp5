package com.carlos.infnet.venda_service.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.carlos.infnet.venda_service.model.ItemTransaction;
import com.carlos.infnet.venda_service.model.Product;
import com.carlos.infnet.venda_service.model.Transaction;
import com.carlos.infnet.venda_service.rabbitmq.EmailProducer;
import com.carlos.infnet.venda_service.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ProductService productService;
    private final EmailProducer emailProducer;

    public void send(Transaction transaction) throws JsonProcessingException {
        emailProducer.send(transaction);
    }
    
    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public BigDecimal calcularValorTotal(Transaction transaction){
        return transaction.getItems().stream()
                .map(this::calcularValorDeItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calcularValorDeItem(ItemTransaction item){
        Product product = productService.getProductById(item.getProductId());
        return product.getPrice().multiply(new BigDecimal(item.getQuantity()));
    }

}
