package com.carlos.infnet.venda_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.carlos.infnet.venda_service.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
