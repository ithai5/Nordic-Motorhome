package com.example.demo.Controller;

import com.example.demo.Model.Motorhome;
import com.example.demo.Service.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MotorhomeController {
    @Autowired
    MotorhomeService motorhomeService;

    @GetMapping("/motorhome/viewMotorhome")
    public String viewMotorhomes(Model model){
        List<Motorhome> motorhomeList = motorhomeService.fetchAll();
        return "/motorhome/viewMotorhome";
    }



}
