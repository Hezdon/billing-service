package com.blusalt.assessment.billingservice.api;

import com.blusalt.assessment.billingservice.entity.Fund;
import com.blusalt.assessment.billingservice.repo.FundRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BillingQueueConsumer {
    @Autowired
    FundRepo fundRepo;

    @RabbitListener(queues = {"${billing.queue.in}"})
    public void receive(@Payload Fund fund) {
        log.info("Fund response received : " + fund);
        fundRepo.save(fund);
    }
}

