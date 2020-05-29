package com.example.demo.Controller;

import com.example.demo.Model.Employee;
import com.example.demo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /*@GetMapping("/login")
    public String createCustomer(){
        return "home/index";
    }
    */
    @PostMapping("/login")
    public String login(@ModelAttribute Employee employee){
        System.out.println(employee);
        if(employeeService.login(employee)==null){
            System.out.println("login fail");
            return "home/index";
        }
        else{
            String title = employeeService.login(employee).getTitle().toLowerCase();
            switch (title){
                case "sales assistant":
                    return "home/logins/logSA";
                case "mechanic":
                    return "home/logins/logM";
                case "bookkeeper":
                    return "home/logins/logB";
                case "owner":
                    return "home/logins/logO";
                default:
                    System.out.println("something went wrong");
                    return "home/index";
            }
        }
    }



}
