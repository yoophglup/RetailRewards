package com.retailgroup.rewards.repository;

import com.retailgroup.rewards.model.Customer;
import com.retailgroup.rewards.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    public Optional<Transaction>findById(Long id);
    Transaction save(Transaction t);


    Iterable<Transaction> findByCustomer(Customer customer);
}
