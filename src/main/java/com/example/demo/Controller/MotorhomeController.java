package com.example.demo.Controller;

import com.example.demo.Model.Motorhome;
import com.example.demo.Service.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MotorhomeController {
    @Autowired
    MotorhomeService motorhomeService;

    // do we include "view motorhomes" option? missing model@addattribute?
    @GetMapping("/motorhome/viewMotorhome")
    public String viewMotorhomes (Model model){
        List<Motorhome> motorhomeList = motorhomeService.fetchAll();
        return "/motorhome/viewMotorhome";
    }

    @GetMapping("/motorhome/createMotorhome")
    public String addMotorhome(){
        return "/motorhome/createMotorhome";
    }

    @PostMapping("/motorhome/createMotorhome")
    public String addMotorhome (@ModelAttribute Motorhome motorhome){
        motorhomeService.addMotorhome(motorhome);
        return "redirect:/";
    }

    @GetMapping("/motorhome/searchMotorhome")
    public String searchMotorhome (@ModelAttribute Motorhome motorhome, Model model){
        List<Motorhome> motorhomeList = motorhomeService.searchMotorhome(motorhome.getLicencePlate());
        model.addAttribute("motorhomes", motorhomeList);
        return "home/motorhome/searchMotorhome";
    }
}
