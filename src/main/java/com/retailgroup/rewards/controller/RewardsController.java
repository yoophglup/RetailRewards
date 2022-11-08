package com.retailgroup.rewards.controller;

import com.retailgroup.rewards.model.*;
import com.retailgroup.rewards.services.CustomerService;
import com.retailgroup.rewards.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RewardsController {
    @Autowired
    CustomerService customerService;
    @Autowired
    TransactionService transactionService;
    @RequestMapping(method = RequestMethod.GET, value= "/customer/{id}")
    public Customer readCustomer(@PathVariable Long id){
        return customerService.getCustomer(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customer/", consumes = "application/json")
    public Customer create(@RequestBody CustomerDTO customerDTO){
        Customer c = customerService.mapper(customerDTO);
        Customer customer = customerService.create(c);
        return customer;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/customer/{id}", consumes ="application/json")
    public Customer update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        Customer c = customerService.getCustomer(id);
        Customer customer = customerService.update(c, customerDTO);
        return customer;
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/customer/{id}")
    public String deleteCustomer(@PathVariable Long id){
    customerService.delete(id);
        return "Customer Delete";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/transaction/{id}", consumes = "application/json")
    public Transaction create(@PathVariable Long id,@RequestBody TransactionDTO transactionDTO){
        Transaction t = transactionService.mapper(transactionDTO);
        t.setCustomer(customerService.getCustomer(id));
        //customerService.getCustomer(id).setTotalRewards(customerService.updateRewards(customerService.getCustomer(id),t.getTransactionTotal()));
        Transaction transaction = transactionService.create(t);
        return transaction;
    }
    @RequestMapping(method = RequestMethod.GET, value= "/transaction/{id}")
    public Transaction readTransaction(@PathVariable Long id){
        return transactionService.getTransaction(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/transaction/{id}")
    public String deleteTransaction(@PathVariable Long id){
        transactionService.delete(id);
        return "Transaction Delete";
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/transaction/{id}", consumes ="application/json")
    public Transaction update(@PathVariable Long id, @RequestBody TransactionDTO transactionDTO){
        Transaction t = transactionService.getTransaction(id);
        Transaction transaction = transactionService.update(t, transactionDTO);
        return transaction;
    }
    @RequestMapping(method = RequestMethod.GET, value= "/getAllTransactions/")
    public Iterable<Transaction> getAlltrans(){
        Iterable<Transaction> c = transactionService.getAllTransaction();
        return c;
    }
    @RequestMapping(method = RequestMethod.GET, value= "/getAllTransactions/{customerId}")
    public Iterable<Transaction> getCustomersTransactions(@PathVariable Long customerId){
        Iterable<Transaction> c = transactionService.getAllCustomersTransactions(customerId);
        return c;
    }
    @RequestMapping(method = RequestMethod.GET, value= "/getCustomersPts/{customerId}")
    public Long getCustomersPts(@PathVariable Long customerId){
        Iterable<Transaction> c = transactionService.getAllCustomersTransactions(customerId);
        Long points=0l;
        points=transactionService.getCustomerPoints(customerId);
        return points;
    }








}
