package com.retailgroup.rewards.services;

import com.retailgroup.rewards.model.Customer;
import com.retailgroup.rewards.model.CustomerDTO;
import com.retailgroup.rewards.model.Transaction;
import com.retailgroup.rewards.model.TransactionDTO;
import com.retailgroup.rewards.repository.RewardsRepository;
import com.retailgroup.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    public Transaction getTransaction(Long id){
        Optional<Transaction> c = transactionRepository.findById(id);
        return c.get();
    }
    public Transaction create(Transaction t){

        return transactionRepository.save(t);
    }
    public Transaction update(Transaction t, TransactionDTO dto){
        if (dto.getTransactionDate() != null){
            t.setTransactionDate(dto.getTransactionDate());
        }
        if (dto.getTransactionTotal() != null){
            t.setTransactionTotal(dto.getTransactionTotal());
        }
        return transactionRepository.save(t);
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
    public Transaction mapper(TransactionDTO dto){
        Transaction t = new Transaction();
        t.setTransactionTotal(dto.getTransactionTotal());
        t.setTransactionDate(dto.getTransactionDate());

        return t;
    }
}
