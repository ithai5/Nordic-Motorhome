package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IdHolder {
    @Id
    private Integer id;

    //CONSTRUCTORS

    public IdHolder ()
    {
    }

    //GETTERS & SETTERS

    public Integer getId ()
    {
        return id;
    }

    public void setId (Integer id)
    {
        this.id = id;
    }
}
