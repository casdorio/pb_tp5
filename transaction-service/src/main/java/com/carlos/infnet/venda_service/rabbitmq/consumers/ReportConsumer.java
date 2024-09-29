package com.carlos.infnet.venda_service.rabbitmq.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReportConsumer {

    @RabbitListener(queues = "report-queue")
    public void receiveReport(String message) {
        log.info("Iniciando a geração de relatório para a transação: {}", message);
        try {
            Thread.sleep(4000);
            log.info("Relatório gerado com sucesso.");
        } catch (InterruptedException e) {
            log.error("Erro ao gerar relatório: ", e);
        }
    }
}
