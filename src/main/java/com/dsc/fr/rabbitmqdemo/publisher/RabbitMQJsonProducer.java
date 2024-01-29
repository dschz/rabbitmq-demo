package com.dsc.fr.rabbitmqdemo.publisher;

import com.dsc.fr.rabbitmqdemo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author DSchneider
 */
@Service
public class RabbitMQJsonProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    @Value("${demo.exchange}")
    private String exchange;

    @Value("${demo.routing_key2}")
    private String jsonRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user) {
        LOGGER.info(String.format("Sending json user : %s", user));
        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
    }
}
