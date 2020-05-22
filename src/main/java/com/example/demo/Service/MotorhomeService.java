package com.example.demo.Service;

import com.example.demo.Model.Motorhome;
import com.example.demo.Repository.MotorhomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorhomeService {
    @Autowired
    MotorhomeRepo motorhomeRepo;

    public List<Motorhome> fetchAll(){
        return motorhomeRepo.fetchAll();
    }

    public Motorhome addMotorhome(Motorhome motorhome){
        return motorhomeRepo.addMotorhome(motorhome);
    }

    public List<Motorhome> searchMotorhome(String keyword){
        return motorhomeRepo.searchMotorhome(keyword);
    }

    public Motorhome findMotorhomeByPlate(String licencePlate){
        return motorhomeRepo.findMotorhomeByPlate(licencePlate);
    }

    public boolean deleteMotorhome(String licencePlate){
        return motorhomeRepo.deleteMotorhome(licencePlate);
    }

    public Motorhome updateMotorhome(Motorhome motorhome){
        return motorhomeRepo.updateMotorhome(motorhome);
    }

}

