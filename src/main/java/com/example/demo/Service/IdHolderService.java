package com.example.demo.Service;

import com.example.demo.Model.Contract;
import com.example.demo.Model.Customer;
import com.example.demo.Model.Motorhome;
import com.example.demo.Repository.IdHolderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdHolderService {
    @Autowired
    IdHolderRepo idHolderRepo;

    public Motorhome findMotorhomeByPlate(String licencePlate) {return idHolderRepo.findMotorhomeByPlate(licencePlate);}

    public Contract findContractById(int contractId) {return idHolderRepo.findContractById(contractId);}

    public Customer findCustomerById(int customerId) {return idHolderRepo.findCustomerById(customerId);}
}
