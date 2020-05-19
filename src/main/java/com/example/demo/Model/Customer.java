package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.Inet4Address;

// this class is part of the model package in final first year project for KEA
@Entity
public class Customer extends Person{
    @Id

    //Customer database table
    private Integer customerId;
    private String phone;
    private String driverNum;
    //Address database table
    private String country;
    private String city;
    private String street;
    private String houseNum;
    private String zip;

    public Customer ()
    {
    }


    public Customer (String firstName, String lastName, String email, int customerId, String phone, String driverNum, String country, String city, String street, String houseNum, String zip)
    {
        super(firstName, lastName, email);
        this.customerId = customerId;
        this.phone = phone;
        this.driverNum = driverNum;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
        this.zip = zip;
    }

    public Integer getCustomerId ()
    {
        return customerId;
    }

    public void setCustomerId (Integer customerId)
    {
        this.customerId = customerId;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getDriverNum ()
    {
        return driverNum;
    }

    public void setDriverNum (String driverNum)
    {
        this.driverNum = driverNum;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getStreet ()
    {
        return street;
    }

    public void setStreet (String street)
    {
        this.street = street;
    }

    public String getHouseNum ()
    {
        return houseNum;
    }

    public void setHouseNum (String houseNum)
    {
        this.houseNum = houseNum;
    }

    public String getZip ()
    {
        return zip;
    }

    public void setZip (String zip)
    {
        this.zip = zip;
    }

    @Override
    public String toString ()
    {
        return "Customer{" + "customerId=" + customerId + ", phone='" + phone + '\'' + ", driverNum='" + driverNum + '\'' + ", country='" + country + '\'' + ", city='" + city + '\'' + ", street='" + street + '\'' + ", houseNum='" + houseNum + '\'' + ", zip='" + zip + '\'' + '}';
    }
}
