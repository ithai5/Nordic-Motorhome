package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){return "home/index";}

    @GetMapping("/manageCustomers")
    public String manageCustomers() {return "home/manageCustomers";}
}
