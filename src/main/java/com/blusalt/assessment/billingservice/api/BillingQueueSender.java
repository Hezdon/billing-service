package com.blusalt.assessment.billingservice.api;

import com.blusalt.assessment.billingservice.entity.Fund;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BillingQueueSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    @Value("${billing.routing.key}")
    String routingKey;

    public void send(Fund fund) {
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, fund);
    }
}
