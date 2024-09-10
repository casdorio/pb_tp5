package com.carlos.infnet.venda_service.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import com.carlos.infnet.venda_service.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailProducer {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;
    public void send(Transaction transaction) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(transaction);
        amqpTemplate.convertAndSend("email-exc","email-rk", message);
    }
}
