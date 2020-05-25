

package com.example.demo.Service;

import com.example.demo.Model.*;

import com.example.demo.Repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Written by Thomas
@Service
public class ContractService{
    @Autowired
    ContractRepo contractRepo;

    public List<Contract> fetchAllContract(){
        return contractRepo.fetchAllContract();
    }

    public void addContract(Contract contract){
        contractRepo.addContract(contract);
    }

    public List<Motorhome> availableMotorhomes(String startDate, String endDate) {return contractRepo.availableMotorhomes(startDate, endDate);}

    public void deleteContract(int contractId) {contractRepo.deleteContract(contractId);}

    public List<Contract> searchContract(String keyword) {return contractRepo.searchForContract(keyword);}

    public double totalContractPrice(int contractId){
        return contractRepo.totalContractPrice(contractId);
    }

    public List<Extra> fetchAllExtra(){
        return contractRepo.fetchAllExtra();
    }

    public List<Transfer> fetchAllTransfer(){return contractRepo.fetchAllTransfer();}

    //From the now defunct idHolderService
    public Contract findContractById(int contractId) {return contractRepo.findContractById(contractId);}

    public Motorhome findMotorhomeByPlate(String licencePlate){return contractRepo.findMotorhomeByPlate(licencePlate);}
    
    public Customer findCustomerById(int customerId){return contractRepo.findCustomerById(customerId);}
}
