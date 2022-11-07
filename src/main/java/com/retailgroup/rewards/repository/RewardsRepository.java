package com.retailgroup.rewards.repository;

import com.retailgroup.rewards.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RewardsRepository extends CrudRepository<Customer,Long> {
    public Optional<Customer>findById(Long id);
    Customer save(Customer c);
}
