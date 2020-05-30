package com.example.demo.Controller;

import com.example.demo.Model.Employee;
import com.example.demo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    static Employee employeeS;

    @PostMapping("/login")
    public String login(@ModelAttribute Employee employee){
        if(employeeService.login(employee)==null) {
            System.out.println("login fail");
            return "home/index";
        }
        else{
            employeeS = employee;
            return "redirect:/mainMenu";
        }
    }

    @PostMapping("/logout")
    public String logOut() {
        employeeS = null;
        return "redirect:/";
    }

    @GetMapping("/mainMenu")
    public String home(Model model) {
        String title = employeeService.login(employeeS).getTitle().toLowerCase();
        model.addAttribute("employee", employeeS);
        switch (title) {
            case "sales assistant":
                return "home/salesAssistantMenu";
            case "mechanic":
                return "home/motorhome/motorhomeMenu";
            case "bookkeeper":
                return "home/bookkeeperMenu";
            case "owner":
                return "home/ownerMenu";
            default:
                System.out.println("something went wrong");
                return "redirect:/";
        }
    }

}
