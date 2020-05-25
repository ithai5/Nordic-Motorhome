package com.example.demo.Controller;

import com.example.demo.Model.Motorhome;
import com.example.demo.Repository.IdHolderRepo;
import com.example.demo.Service.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MotorhomeController {
    @Autowired
    MotorhomeService motorhomeService;


    //VIEW ALL
    @GetMapping("/motorhome/viewMotorhome")
    public String viewMotorhomes (Model model){
        List<Motorhome> motorhomeList = motorhomeService.fetchAll();
        model.addAttribute("motorhomes", motorhomeList);
        return "home/motorhome/viewMotorhome";
    }


    //ADD
    @GetMapping("/motorhome/createMotorhome")
    public String addMotorhome(){
        return "home/motorhome/createMotorhome";
    }

    @PostMapping("/motorhome/createMotorhome")
    public String addMotorhome (@ModelAttribute Motorhome motorhome){
        motorhomeService.addMotorhome(motorhome);
        return "redirect:/";
    }


    //SEARCH
    @GetMapping("/motorhome/searchMotorhome")
    public String searchMotorhome (@ModelAttribute Motorhome motorhome, Model model){
        List<Motorhome> motorhomeList = motorhomeService.searchMotorhome(motorhome.getLicencePlate());
        if(motorhomeList.isEmpty()){
            return "home/motorhome/noSearchResultsMh";
        }
        model.addAttribute("motorhomes", motorhomeList);
        return "home/motorhome/searchMotorhome";
    }


    //DELETE
    @GetMapping("/motorhome/deleteMotorhome/{licencePlate}")
    public String deleteMotorhome(@PathVariable("licencePlate") String licencePlate){
        Boolean delete = motorhomeService.deleteMotorhome(licencePlate);
        return "redirect:/";
    }


    //UPDATE
    @GetMapping("/motorhome/updateMotorhome/{licencePlate}")
    public String updateMotorhome(@PathVariable("licencePlate") String licencePlate, Model model){
        model.addAttribute("motorhome", motorhomeService.findMotorhomeByPlate(licencePlate));
        return "home/motorhome/updateMotorhome";
    }

    @PostMapping("/motorhome/updateMotorhome")
    public String updateMotorhome(@ModelAttribute Motorhome motorhome){
        System.out.println(motorhome.getLicencePlate());
        motorhomeService.updateMotorhome(motorhome);
        return "redirect:/";
    }
}
