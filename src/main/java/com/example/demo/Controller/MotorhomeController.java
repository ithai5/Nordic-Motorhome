package com.example.demo.Controller;

import com.example.demo.Model.Motorhome;
import com.example.demo.Service.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MotorhomeController {
    @Autowired
    MotorhomeService motorhomeService;

    //Previous Get method
    //VIEW ALL
    @PostMapping("/motorhome/viewMotorhome")
    public String viewMotorhomes (Model model){
        List<Motorhome> motorhomeList = motorhomeService.fetchAll();
        model.addAttribute("motorhomes", motorhomeList);
        return "home/motorhome/viewMotorhome";
    }

    //Previous Get method
    //ADD
    @PostMapping("/motorhome/createMotorhome")
    public String createMotorhome(){
        return "home/motorhome/createMotorhome";
    }

    @PostMapping("/motorhome/addMotorhome")
    public String addMotorhome (@ModelAttribute Motorhome motorhome){
        motorhomeService.addMotorhome(motorhome);
        return "home/motorhome/actionSuccessful";
    }

    //Previous Get method
    //SEARCH
    @PostMapping("/motorhome/searchMotorhome")
    public String searchMotorhome (@ModelAttribute Motorhome motorhome, Model model){
        List<Motorhome> motorhomeList = motorhomeService.searchMotorhome(motorhome.getLicencePlate());
        if(motorhomeList.isEmpty()||motorhomeList==null){
            return "home/motorhome/noSearchResultsMh";
        }
        model.addAttribute("motorhomes", motorhomeList);
        return "home/motorhome/searchMotorhome";
    }

    //Previous Get method
    //DELETE
    @PostMapping("/motorhome/deleteMotorhome/{licencePlate}")
    public String deleteMotorhome(@PathVariable("licencePlate") String licencePlate){
        Boolean delete = motorhomeService.deleteMotorhome(licencePlate);
        return "home/motorhome/actionSuccessful";
    }

    //Previous Get method
    //UPDATE
    @PostMapping("/motorhome/updateMotorhome/{licencePlate}")
    public String updateMotorhome(@PathVariable("licencePlate") String licencePlate, Model model){
        model.addAttribute("motorhome", motorhomeService.findMotorhomeByPlate(licencePlate));
        return "home/motorhome/updateMotorhome";
    }

    //Previous Get method
    @PostMapping("/motorhome/updateMotorhome")
    public String updateMotorhome(@ModelAttribute Motorhome motorhome){
        motorhomeService.updateMotorhome(motorhome);
        return "home/motorhome/actionSuccessful";
    }
}
