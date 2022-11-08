package com.retailgroup.rewards.services;

import com.retailgroup.rewards.model.Customer;
import com.retailgroup.rewards.model.CustomerDTO;
import com.retailgroup.rewards.model.Transaction;
import com.retailgroup.rewards.repository.RewardsRepository;
import com.retailgroup.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerService {
    @Autowired
    RewardsRepository rewardsRepository;
    public Customer getCustomer(Long id){
        Optional<Customer> c = rewardsRepository.findById(id);
        return c.get();
    }
    public Customer create(Customer c){
        return rewardsRepository.save(c);
    }
    public Customer update(Customer c, CustomerDTO dto){
        if(dto.getFirstName() != null){
            //Fix name issue
            c.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            c.setLastName(dto.getLastName());
        }
//        if (dto.getTotalRewards() != null){
//            c.setTotalRewards(dto.getTotalRewards());
//        }
        if (dto.getCreateDate() != null){
            c.setCreateDate(dto.getCreateDate());
        }

        return rewardsRepository.save(c);
    }

    public void delete(Long id) {
        rewardsRepository.deleteById(id);
    }
    public Customer mapper(CustomerDTO dto){
        Customer c = new Customer();
        c.setFirstName(dto.getFirstName());
        c.setLastName(dto.getLastName());
        c.setCreateDate(dto.getCreateDate());
        //c.setTotalRewards(dto.getTotalRewards());
        return c;
    }

//    public Long updateRewards(Customer dto, Long transactionAmount){
//        //Example ex: a $120 purchase = 2x$20 + 1x$50 = 90 points
//        //Breakdown: 2 points * everydollar over 100 (20x2)
//        //		    1 point * everydollar over 50 but under 100 = 50
//        //Long existingRewards = dto.getTotalRewards();
//        Long newRewards = 0l;
//        if (transactionAmount > 100)
//        {
//            newRewards=((transactionAmount-100) * 2) + 50;
//        } else {
//            if (transactionAmount > 50) {
//                newRewards = (transactionAmount - 50) * 1;
//            }
//        }
//        //newRewards=newRewards+existingRewards;
//        return newRewards;
//    }
    public Long getPoints(long userId){
        TransactionRepository transactionRepository;

        Long total=0L;

        return 0l;
    }

}
