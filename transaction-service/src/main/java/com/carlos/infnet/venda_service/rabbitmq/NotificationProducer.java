package com.carlos.infnet.venda_service.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import com.carlos.infnet.venda_service.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public void sendNotification(Transaction transaction) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(transaction);
        amqpTemplate.convertAndSend("notification-exc", "email-rk", message);
        amqpTemplate.convertAndSend("notification-exc", "sms-rk", message);
        amqpTemplate.convertAndSend("notification-exc", "report-rk", message);
    }

}

