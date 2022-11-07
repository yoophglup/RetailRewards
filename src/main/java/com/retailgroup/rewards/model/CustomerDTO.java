package com.retailgroup.rewards.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerDTO {
    Long totalRewards;
    String firstName;
    String lastName;
    Date createDate;

}
