package com.blusalt.assessment.billingservice.service;

import com.blusalt.assessment.billingservice.api.BillingQueueSender;
import com.blusalt.assessment.billingservice.dto.CustomerFund;
import com.blusalt.assessment.billingservice.entity.Fund;
import com.blusalt.assessment.billingservice.repo.FundRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BillingService {

    @Autowired
    FundRepo fundRepo;

    @Autowired
    BillingQueueSender billingQueueSender;

    public CustomerFund logFund(CustomerFund custFund) {

        Fund fund = new Fund(custFund);
        log.info("logging request to database {} ", fund);
        fundRepo.save(fund);

        log.info("sending fund request to billing queue {} ", fund);
        billingQueueSender.send(fund);

        return custFund;
    }
}
