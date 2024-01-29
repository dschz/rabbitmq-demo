package com.dsc.fr.rabbitmqdemo.consumer;

import com.dsc.fr.rabbitmqdemo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author DSchneider
 */
@Service
public class RabbitMQJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = {"${demo.queue2}"})
    public void consumeJsonMsg(User user){
        LOGGER.info(String.format("Received message USER json : %s", user.toString()));
    }
}
