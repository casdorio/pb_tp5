package com.carlos.infnet.venda_service.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import com.carlos.infnet.venda_service.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public void sendNotification(Transaction transaction) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(transaction);    
        sendToSMSQueue(message);
        sendToEmailQueue(message);
        sendToReportQueue(message);
    }

    public void sendToSMSQueue(String message) {
        amqpTemplate.convertAndSend("sms-queue", message);
    }

    public void sendToEmailQueue(String message) {
        amqpTemplate.convertAndSend("email-queue", message);
    }

    public void sendToReportQueue(String message) {
        amqpTemplate.convertAndSend("report-queue", message);
    }

    public void sendToRetryQueue(String message, String errorDetails) {
        amqpTemplate.convertAndSend("retry-queue", message);
    }

    public void sendToErrorQueue(String message, String errorDetails) {
        amqpTemplate.convertAndSend("error-queue", message);
    }
}
