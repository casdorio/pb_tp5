package com.carlos.infnet.venda_service.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.carlos.infnet.venda_service.model.TaxResponsePayload;
import com.carlos.infnet.venda_service.model.Transaction;

@FeignClient(name = "VATRATE")
public interface TaxClient {
    
    @PostMapping("/")
    TaxResponsePayload getTaxByItems(@RequestBody Transaction transaction);
}
