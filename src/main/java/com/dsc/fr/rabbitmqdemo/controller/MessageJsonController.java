package com.dsc.fr.rabbitmqdemo.controller;

import com.dsc.fr.rabbitmqdemo.dto.User;
import com.dsc.fr.rabbitmqdemo.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DSchneider
 */
@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
    private RabbitMQJsonProducer producer;

    public MessageJsonController(RabbitMQJsonProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
        producer.sendJsonMessage(user);
        return ResponseEntity.ok("Json User sent ok");
    }
}
