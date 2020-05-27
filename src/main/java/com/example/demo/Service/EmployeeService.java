// this class is part of the model package in final first year project for KEA
//Made By Itai Gramse
package com.example.demo.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;

    public Employee login(Employee employee){
        return employeeRepo.login(employee);
    }
}
