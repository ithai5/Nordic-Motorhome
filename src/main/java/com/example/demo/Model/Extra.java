package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Extra {
    @Id
    private int extraId;
    private int pricePerDay;
    private String extraName;
    private int amount;

    public Extra ()
    {
    }

    public int getPricePerDay ()
    {
        return pricePerDay;
    }

    public void setPricePerDay (int pricePerDay)
    {
        this.pricePerDay = pricePerDay;
    }

    public int getExtraId ()
    {
        return extraId;
    }

    public void setExtraId (int extraId)
    {
        this.extraId = extraId;
    }

    public String getExtraName ()
    {
        return extraName;
    }

    public void setExtraName (String extraName)
    {
        this.extraName = extraName;
    }

    public int getAmount ()
    {
        return amount;
    }

    public void setAmount (int amount)
    {
        this.amount = amount;
    }

    @Override
    public String toString ()
    {
        return "Extra{" + "extraId=" + extraId + ", extraName=" + extraName  + ", amount=" + amount + '}';
    }
}
