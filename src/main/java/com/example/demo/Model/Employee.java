package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee extends Person{
    @Id
    private int EmployeeId;
    private String title;
    private String passwordDetails;

    public Employee ()
    {
    }

    public int getEmployeeId ()
    {
        return EmployeeId;
    }

    public void setEmployeeId (int employeeId)
    {
        EmployeeId = employeeId;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getPasswordDetails ()
    {
        return passwordDetails;
    }

    public void setPasswordDetails (String passwordDetails)
    {
        this.passwordDetails = passwordDetails;
    }

    @Override
    public String toString ()
    {
        return "Employee{" + "EmployeeId=" + EmployeeId + ", title='" + title + '\'' + ", passwordDetails='" + passwordDetails + '\'' + "} " + super.toString();
    }
}
