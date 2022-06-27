package com.blusalt.assessment.billingservice.api;

import com.blusalt.assessment.billingservice.dto.CustomerFund;
import com.blusalt.assessment.billingservice.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/billing")
public class BillingController {

    @Autowired
    BillingService billingService;


    @PostMapping(value = "/customer/fund", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fundCustomer(@RequestBody @Valid CustomerFund fund){

        return new ResponseEntity<>(billingService.logFund(fund).withMessage("Pending"), HttpStatus.OK);
    }
}
