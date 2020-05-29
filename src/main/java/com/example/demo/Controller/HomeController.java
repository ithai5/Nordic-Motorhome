package com.example.demo.Controller;

import com.example.demo.Model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){return "home/index";}

    @PostMapping("/salesAssistantMenu")
    public String saMenu() {
        return "home/salesAssistantMenu";
    }

    @PostMapping("/ownerMenu")
    public String oMenu() {
        return "home/ownerMenu";
    }

    @PostMapping("/customer")
    public String manageCustomer(){return "home/customer/customerMenu";}

    @PostMapping("/motorhome")
    public String manageMotorhome(){return "home/motorhome/motorhomeMenu";}

}