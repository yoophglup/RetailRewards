package com.retailgroup.rewards.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import lombok.Getter;
        import lombok.Setter;

        import java.util.Date;

@Getter
@Setter
public class TransactionDTO {
    Date transactionDate;
    Long transactionTotal;
    Long customer;

}
