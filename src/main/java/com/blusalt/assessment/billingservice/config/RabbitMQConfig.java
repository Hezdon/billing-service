package com.blusalt.assessment.billingservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMQConfig {

    @Value("${billing.queue.out}")
    String billingQueueOut;

    @Value("${billing.routing.key}")
    String billingRoutingKey;

    @Value("${spring.rabbitmq.host}")
    String rabbitmqUrl;

    @Value("${spring.rabbitmq.port}") int rabbitmqPort;

    @Value("${spring.rabbitmq.username}") String rabbitmqUsername;

    @Value("${spring.rabbitmq.password}") String rabbitmqPassword;

    @Value("${billing.direct.exchange}") String billingExchange;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitmqUrl);
        connectionFactory.setUsername(rabbitmqUsername);
        connectionFactory.setPassword(rabbitmqPassword);
        connectionFactory.setPort(rabbitmqPort);
        return connectionFactory;
    }

    @Bean
    public Queue billingQueueOut() {
        return new Queue(billingQueueOut, true);
    }

    @Bean
    DirectExchange billingExchange() {
        return new DirectExchange(billingExchange);
    }

    @Bean
    Binding billingBinding(Queue billingQueueOut, DirectExchange exchange) {
        return BindingBuilder.bind(billingQueueOut).to(exchange).with(billingRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
