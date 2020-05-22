package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){return "home/index";}

    @GetMapping("/customer")
    public String manageCustomer(){return "home/customer";}

    @GetMapping("/motorhome")
    public String manageMotorhome(){return "home/motorhome";}

    //@GetMapping("/contract")
    //public String manageContract(){return "home/contract";}
}