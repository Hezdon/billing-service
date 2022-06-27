package com.blusalt.assessment.billingservice.entity;

import com.blusalt.assessment.billingservice.dto.CustomerFund;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fund")
public class Fund implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    long customerId;

    String amount;

    String transactionId;

    String status;

    Date createdOn;

    public Fund(CustomerFund custFund) {
        amount = custFund.getAmount();
        customerId = custFund.getCustomerId();
        status = "Pending";
        createdOn = new Date();
        transactionId = UUID.randomUUID().toString();
    }
}
