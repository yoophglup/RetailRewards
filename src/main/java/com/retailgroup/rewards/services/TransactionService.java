package com.retailgroup.rewards.services;

import com.retailgroup.rewards.model.Customer;
import com.retailgroup.rewards.model.CustomerDTO;
import com.retailgroup.rewards.model.Transaction;
import com.retailgroup.rewards.model.TransactionDTO;
import com.retailgroup.rewards.repository.RewardsRepository;
import com.retailgroup.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.StreamSupport;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

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
    public Iterable getAllTransaction(){
        Iterable<Transaction> c = transactionRepository.findAll();
        return c;
    }

    @Autowired
    CustomerService customerService;
    public Iterable<Transaction> getAllCustomersTransactions(Long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        Iterable<Transaction> ct= transactionRepository.findByCustomer(customer);
        return ct;
    }

    public Long getCustomerPoints(Long customerId) {
        Iterable<Transaction> allTrans = getAllCustomersTransactions(customerId);
        Long totalPoints=0l;
        for (Transaction transaction : allTrans) {
            SimpleDateFormat sdf = new SimpleDateFormat(transaction.getTransactionDate().toString());
            System.out.println(sdf.toString());
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            System.out.println(sdf.toString());

            if (transaction.getTransactionTotal() > 100){
                totalPoints=totalPoints+((transaction.getTransactionTotal()-100) * 2) + 50;
        } else {
                if (transaction.getTransactionTotal() > 50) {
                    totalPoints = totalPoints+transaction.getTransactionTotal() - 50;
                }
            }
        }
        return totalPoints;
    }
}

