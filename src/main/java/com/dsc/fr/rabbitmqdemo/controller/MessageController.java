package com.dsc.fr.rabbitmqdemo.controller;

import com.dsc.fr.rabbitmqdemo.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DSchneider
 */
@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private RabbitMQProducer producer;

    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    //ex http://localhost:8080/api/v1/publish?msg=houhou
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("msg") String message) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent OK");
    }
}
