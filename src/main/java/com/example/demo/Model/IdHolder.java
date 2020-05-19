package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IdHolder {
    @Id
    private Integer customerId;
    private Integer addressId;

    public IdHolder ()
    {
    }

    public Integer getCustomerId ()
    {
        return customerId;
    }

    public void setCustomerId (Integer customerId)
    {
        this.customerId = customerId;
    }

    public Integer getAddressId ()
    {
        return addressId;
    }

    public void setAddressId (Integer addressId)
    {
        this.addressId = addressId;
    }

    @Override
    public String toString ()
    {
        return "IdHolder{" + "customerId=" + customerId + ", addressId=" + addressId + '}';
    }
}
