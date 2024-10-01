package com.carlos.infnet.venda_service.rabbitmq.consumers;

import java.util.Random;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.carlos.infnet.venda_service.model.Transaction;
import com.carlos.infnet.venda_service.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SMSConsumer {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final int MAX_RETRIES = 2;

    @RabbitListener(queues = "sms-queue")
    public void receiveSMS(String message) {

        String[] messageParts = message.split(";");
        String originalMessage = messageParts[0];
        int retryCount = messageParts.length > 1 ? Integer.parseInt(messageParts[1]) : 0;

        try {
            Transaction transaction = objectMapper.readValue(message, Transaction.class);            
            log.info("Processando envio de SMS para a transação ID: {}", transaction.getId());

            boolean responseReceived = waitForSMSResponse(transaction);
            
            if (responseReceived) {
                log.info("SMS enviado com sucesso para a transação ID: {}", transaction.getId());
                notificationService.updateNotificationStatus(transaction.getId());
                return; 
            } else {
                retryCount++;
                if (retryCount <= MAX_RETRIES) {
                    String updatedMessage = originalMessage + ";" + retryCount;
                    log.info("Reevio SMS para a transação ID: {}", transaction.getId());
                    notificationService.sendToSMSQueue(updatedMessage);
                } else {
                    log.error("Máximo de tentativas atingido para a transação ID: {}. Enviando para a fila de erro.", transaction.getId());
                    notificationService.sendToErrorQueue(message, "Máximo de tentativas atingido.");
                }            }
        } catch (Exception e) {
            log.error("Erro ao enviar SMS: ", e);
            notificationService.sendToErrorQueue(message, "Não recebeu confirmação de envio.");
        } 
    }

    private boolean waitForSMSResponse(Transaction transaction) throws InterruptedException {
        Thread.sleep(15000);
        Random random = new Random();
        return random.nextBoolean(); 
    }
}
