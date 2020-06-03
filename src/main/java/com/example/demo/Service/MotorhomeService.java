package com.example.demo.Service;

import com.example.demo.Model.Motorhome;
import com.example.demo.Repository.MotorhomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Made by Ignacio


//This annotation informs the Spring framework that this class is a Repository class
@Service
public class MotorhomeService{
    @Autowired
    MotorhomeRepo motorhomeRepo; // Hereby we connect the Service class and the Repository class

    public List<Motorhome> fetchAll(){
        return motorhomeRepo.fetchAll();
    }

    public Motorhome addMotorhome(Motorhome motorhome){
        return motorhomeRepo.addMotorhome(motorhome);
    }

    public List<Motorhome> searchMotorhome(String keyword){
        return motorhomeRepo.searchMotorhome(keyword);
    }

    public Motorhome findMotorhomeByPlate(String licencePlate){return motorhomeRepo.findMotorhomeByPlate(licencePlate);}

    public boolean deleteMotorhome(String licencePlate){
        return motorhomeRepo.deleteMotorhome(licencePlate);
    }

    public Motorhome updateMotorhome(Motorhome motorhome){
        return motorhomeRepo.updateMotorhome(motorhome);
    }

}



