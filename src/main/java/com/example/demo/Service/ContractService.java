

package com.example.demo.Service;

import com.example.demo.Model.Contract;
import com.example.demo.Model.Customer;
import com.example.demo.Model.Motorhome;
import com.example.demo.Repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Written by Thomas
@Service
public class ContractService {
    @Autowired
    ContractRepo contractRepo;

    public List<Contract> fetchAll(){
        return contractRepo.fetchAll();
    }

    public void addContract(Contract contract, int customerId, String licencePlate){
        contractRepo.addContract(contract, customerId, licencePlate);
    }

    public List<Motorhome> availableCars(String startDate, String endDate) {return contractRepo.availableCars(startDate, endDate);}
    /*
    public List<Customer> searchForCustomer(String keyword){
        return customerRepo.searchForCustomer(keyword);
    }

    public Customer findCustomerById(int customerId)
    {
        return customerRepo.findCustomerById(customerId);
    }

    public Boolean deleteCustomer (int customerId){
        return customerRepo.deleteCustomer(customerId);
    }
    public Customer updateCustomer(Customer customer){
        return customerRepo.updateCustomer(customer);
    }
    */
}
