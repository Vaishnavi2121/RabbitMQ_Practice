package com.rabbitmq.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(String message) {
        try {
            LOGGER.info("Received message-> {}", message);
        } catch (Exception e) {
            LOGGER.error("Error Receiving message", e);
        }
    }
}
