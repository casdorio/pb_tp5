package com.carlos.infnet.venda_service.service;

import org.springframework.stereotype.Service;

import com.carlos.infnet.venda_service.model.TaxResponsePayload;
import com.carlos.infnet.venda_service.model.Transaction;
import com.carlos.infnet.venda_service.service.feign.TaxClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaxService {
        private final TaxClient taxClient;

        public TaxResponsePayload getTaxByItems(Transaction transaction) {                
                return taxClient.getTaxByItems(transaction);
        }
}
