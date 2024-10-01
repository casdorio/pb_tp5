package com.carlos.infnet.venda_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange("notification-exc");
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("email-queue", true);
    }

    @Bean
    public Queue smsQueue() {
        return new Queue("sms-queue", true);
    }

    @Bean
    public Queue reportQueue() {
        return new Queue("report-queue", true);
    }

    @Bean
    public Queue retryQueue() {
        return new Queue("retry-queue", true);
    }

    @Bean
    public Queue errorQueue() {
        return new Queue("error-queue", true);
    }

    @Bean
    public Binding emailBinding(Queue emailQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(emailQueue).to(notificationExchange).with("email-rk");
    }

    @Bean
    public Binding smsBinding(Queue smsQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(smsQueue).to(notificationExchange).with("sms-rk");
    }

    @Bean
    public Binding reportBinding(Queue reportQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(reportQueue).to(notificationExchange).with("report-rk");
    }

    @Bean
    public Binding retryBinding(Queue retryQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(retryQueue).to(notificationExchange).with("retry-rk");
    }

    @Bean
    public Binding errorBinding(Queue errorQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(errorQueue).to(notificationExchange).with("error-rk");
    }
}