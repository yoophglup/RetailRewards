package com.retailgroup.rewards.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Transactions")
@Getter
@Setter
public class Transaction extends BaseEntity{
    Date transactionDate;
    Long transactionTotal;

    @ManyToOne
    Customer customer;

}
