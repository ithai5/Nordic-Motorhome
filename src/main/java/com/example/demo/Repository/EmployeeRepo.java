package com.example.demo.Repository;

import com.example.demo.Model.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeRepo extends IdHolderRepo{
    public Employee login(Employee employee){
        if(preventSql(employee.toString())){
            System.out.println("sql injection");
            return null;
        }
        String sql = "SELECT * FROM KeaProject.Employee e " +
                     "WHERE e.firstName LIKE '" + employee.getFirstName() +"'" +
                     "AND e.lastName LIKE '" + employee.getLastName() + "'" +
                     " AND e.passwordDetails LIKE '" + employee.getPasswordDetails() + "'";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        List<Employee> employeeList = template.query(sql , rowMapper);
        if (employeeList.isEmpty()){
            System.out.println("list is empty");
            return null;
        }
        else{
            return employeeList.get(0);
        }
    }

}
