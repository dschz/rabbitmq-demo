package com.dsc.fr.rabbitmqdemo.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author DSchneider
 */
@Service
public class RabbitMQProducer {
    private static Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    @Value("${demo.exchange}")
    private String exchange;

    @Value("${demo.routing_key1}")
    private String routingKey1;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info(String.format("Sending message : %s", message));

        rabbitTemplate.convertAndSend(exchange, routingKey1, message);
    }
}
