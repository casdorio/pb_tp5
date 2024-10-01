package com.carlos.infnet.venda_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.infnet.venda_service.model.Transaction;
import com.carlos.infnet.venda_service.rabbitmq.NotificationProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class NotificationService {

    @Autowired
    private TransactionService transactionService;

    @Autowired 
    private NotificationProducer notificationProducer;


    public void send(Transaction transaction) throws JsonProcessingException {
        notificationProducer.sendNotification(transaction);
    }

    public void updateNotificationStatus(String transactionId) {
        Transaction transaction = transactionService.getTransactionById(transactionId);

        transaction.setNotificationsProcessed(transaction.getNotificationsProcessed() + 1);

        if (transaction.getNotificationsProcessed() == 3) {
            transaction.setStatusNotification("FINALIZADO");
            transaction.addStatusChange(transaction.getStatusNotification());
        }

        transactionService.save(transaction);
    }

    public void sendToRetryQueue(String message, String errorDetails) {
        notificationProducer.sendToRetryQueue(message, errorDetails);
    }

    public void sendToErrorQueue(String message, String errorDetails) {
        notificationProducer.sendToErrorQueue(message, errorDetails);
    }

    public void sendToSMSQueue(String message) {
        notificationProducer.sendToSMSQueue(message);
    }

    public void sendToEmailQueue(String message) {
        notificationProducer.sendToEmailQueue(message);
    }

    public void sendToReportQueue(String message) {
        notificationProducer.sendToReportQueue(message);
    }

}
