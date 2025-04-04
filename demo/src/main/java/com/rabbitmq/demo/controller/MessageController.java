package com.rabbitmq.demo.controller;


import com.rabbitmq.demo.publisher.RabbitMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private RabbitMqProducer producer;

    @Autowired
    public MessageController(RabbitMqProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        try {
            producer.sendMessage(message);
            return ResponseEntity.ok("Message sent to Rabbit MQ...");
        } catch (Exception e) {
           return ResponseEntity
                   .status(500)
                   .body("Failed to send message: ");
        }
    }
}
