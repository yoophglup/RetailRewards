package com.retailgroup.rewards.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Customer")
@Getter
@Setter
public class Customer extends BaseEntity{
    String firstName;
    String lastName;
    Date createDate;
    //Long totalRewards;
}
