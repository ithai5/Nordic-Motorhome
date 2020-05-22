package com.example.demo.Service;

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

    public Motorhome searchMotorhomeByPlate(String licencePlate) {return idHolderRepo.findMotorhomeByPlate(licencePlate);}
}
