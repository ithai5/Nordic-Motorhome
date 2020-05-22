// this class is part of the model package in final first year project for KEA
//Made By Itai Gramse

package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer extends Person{
    @Id

    //Customer database table
    private int customerId;
    private String phone;
    private String driverNum;
    //Address database table
    private String country;
    private String city;
    private String street;
    private String houseNum;
    private String zip;
    private int addressId;

    public Customer ()
    {
    }


    public Customer (String firstName, String lastName, String email, Integer customerId, String phone, String driverNum, String country, String city, String street, String houseNum, String zip, Integer addressId)
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
        this.addressId = addressId;
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

    public void setAddressId (Integer addressId)
    {
        this.addressId = addressId;
    }

    public Integer getAddressId ()
    {
        return addressId;
    }

    @Override
    public String toString ()
    {
        return  super.toString() + "Customer{" + "customerId=" + customerId + ", phone='" + phone + '\'' + ", driverNum='" + driverNum + '\'' + ", country='" + country + '\'' + ", city='" + city + '\'' + ", street='" + street + '\'' + ", houseNum='" + houseNum + '\'' + ", zip='" + zip + '\'' + ", addressId=" + addressId + "} " ;
    }
}
