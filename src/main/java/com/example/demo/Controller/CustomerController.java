package com.example.demo.Controller;

import com.example.demo.Model.Customer;
import com.example.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/createCustomer")
    public String createCustomer(){
        return "home/customer/createCustomer";
    }

    @PostMapping("/customer/createCustomer")
    public String createCustomer(@ModelAttribute Customer customer){
        customerService.addCustomer(customer);
        return "redirect:/";
    }
/*
    @GetMapping("/customer/searchCustomer")
    public String searchCustomer(){
        System.out.println("getmapping");
        return "home/searchCustomer";
    }
*/
    @GetMapping("/customer/searchCustomer/")
    public String searchCustomer(@ModelAttribute Customer customer, Model model){
        List<Customer> customerList = customerService.searchForCustomer( customer.getFirstName());
        model.addAttribute("customers", customerList);
        return "home/customer/searchCustomer";
    }

    @GetMapping("/customer/deleteCustomer/{customerId}")
    public String deleteCustomer(@PathVariable("customerId") int customerId){
        Boolean delete = customerService.deleteCustomer(customerId);
        return "redirect:/";
    }

    @GetMapping("/customer/updateCustomer/{customerId}")
    public String updateCustomer(@PathVariable("customerId") int customerId, Model model){
        model.addAttribute("customer",customerService.findCustomerById(customerId));
        return "home/customer/updateCustomer";
    }

    @PostMapping("/customer/updateCustomer")
    public String updateCustomer(@ModelAttribute Customer customer){
        System.out.println(customer);
        customerService.updateCustomer(customer);
        return "redirect:/";
    }






}