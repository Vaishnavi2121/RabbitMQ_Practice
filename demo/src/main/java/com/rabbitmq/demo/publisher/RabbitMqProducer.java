package com.rabbitmq.demo.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routingkey.name}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        try {
            LOGGER.info(String.format("Message sent-->%s", message));
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
        } catch (AmqpException e) {
            LOGGER.error("Failed to send Message",e);
        }
    }
}
