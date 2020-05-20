package com.example.demo.Service;

import com.example.demo.Model.Customer;
import com.example.demo.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
@Autowired
    CustomerRepo customerRepo;

    public List<Customer> fetchAll(){
        return customerRepo.fetchAll();
    }

    public Customer addCustomer(Customer customer){
        return customerRepo.addCustomer(customer);
    }

    public List<Customer> searchForCustomer(String keyword){
        return customerRepo.searchForCustomer(keyword);
    }
}
