package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IdHolder {
    @Id
    private Integer customerId;
    private Integer addressId;
    private Integer mhTypeId;
    private Integer mhSpecsId;

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

    public Integer getMhTypeId () {
        return mhTypeId;
    }

    public void setMhTypeId (Integer mhTypeId) {
        this.mhTypeId = mhTypeId;
    }

    public Integer getMhSpecsId () {
        return mhSpecsId;
    }

    public void setMhSpecsId (Integer mhSpecsId) {
        this.mhSpecsId = mhSpecsId;
    }

    @Override
    public String toString ()
    {
        return "IdHolder{" + "customerId=" + customerId + ", addressId=" + addressId + '}';
    }
}
