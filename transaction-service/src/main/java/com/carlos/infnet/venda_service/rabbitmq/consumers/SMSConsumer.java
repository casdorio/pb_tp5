package com.carlos.infnet.venda_service.rabbitmq.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SMSConsumer {

    @RabbitListener(queues = "sms-queue")
    public void receiveSMS(String message) {
        log.info("Processando envio de SMS para a transação: {}", message);
        try {
            Thread.sleep(10500);
            log.info("SMS enviado com sucesso.");
        } catch (InterruptedException e) {
            log.error("Erro ao enviar SMS: ", e);
        }
    }
}
