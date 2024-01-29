package com.dsc.fr.rabbitmqdemo.config;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DSchneider
 */
@Configuration
public class RabbitMQConfig {
    // spring bean for queue
    @Value("${demo.queue1}")
    private String strQueue1;

    @Value("${demo.queue2}")
    private String strQueue2;

    @Value("${demo.exchange}")
    private String exchange;

    @Value("${demo.routing_key1}")
    private String routingKey1;

    @Value("${demo.routing_key2}")
    private String routingKey2;

    @Bean
    public Queue queue() {
        return new Queue(strQueue1);
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue(strQueue2);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey1);
        //return BindingBuilder.bind(new Queue("queue_demo")).to(new DirectExchange("exchange_demo")).with(routingKey1);
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue()).to(exchange()).with(routingKey2);
    }



    //en principe il faudrait ajouter les confs de :
    // ConnectionFactory

    // RabbitTemplate
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    // RabbitAdmin
    // Mais Spring Framework le fait par defaut
}
