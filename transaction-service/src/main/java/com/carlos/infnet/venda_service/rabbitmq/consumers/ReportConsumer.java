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
public class ReportConsumer {
    
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "report-queue")
    public void receiveReport(String message) {
            try {
                Transaction transaction = objectMapper.readValue(message, Transaction.class);
                log.info("Iniciando a geração de relatório para a transação ID: {}", transaction.getId());

                Thread.sleep(4000); 

                log.info("Relatório gerado com sucesso para a transação ID: {}", transaction.getId());

                notificationService.updateNotificationStatus(transaction.getId());

                return;
            } catch (InterruptedException e) {
                log.error("Erro ao gerar relatório: ", e);
            } catch (Exception e) {
                log.error("Erro ao processar a transação: {}, Tentativa: {}", message);
                notificationService.sendToErrorQueue(message, "Erro ao gerar relatório após várias tentativas.");
        
        }
    }
}
