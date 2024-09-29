package com.carlos.infnet.venda_service.rabbitmq.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailConsumer {

    @RabbitListener(queues = "email-queue")
    public void receiveEmail(String message) {
        log.info("Processando envio de e-mail para a transação: {}", message);
        try {
            Thread.sleep(10000);
            log.info("E-mail enviado com sucesso.");
        } catch (InterruptedException e) {
            log.error("Erro ao enviar e-mail: ", e);
        }
    }
}
