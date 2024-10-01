package com.carlos.infnet.venda_service.rabbitmq.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.carlos.infnet.venda_service.model.Transaction;
import com.carlos.infnet.venda_service.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailConsumer {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "email-queue")
    public void receiveEmail(String message) {
        try {
            Transaction transaction = objectMapper.readValue(message, Transaction.class);

            log.info("Processando envio de e-mail para a transação ID: {}", transaction.getId());

            Thread.sleep(10000);

            log.info("E-mail enviado com sucesso para a transação ID: {}", transaction.getId());

            notificationService.updateNotificationStatus(transaction.getId());

            return;

        } catch (InterruptedException e) {
            log.error("Erro ao enviar e-mail: ", e);
        } catch (Exception e) {
            log.error("Erro ao processar a transação: {}, Tentativa: {}", message);
            notificationService.sendToErrorQueue(message, "Erro ao enviar e-mail após várias tentativas.");

        }
    }
}
