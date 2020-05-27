package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transfer {
    @Id
    //Attributes
    private int transferId;
    private String location;
    //Consider changing to a double
    private int distance;
    //Consider removing from the database
    private double price;

    //CONSTRUCTORS
    public Transfer() {}

    public Transfer(int transferId, String location, int distance, double price) {
        this.transferId = transferId;
        this.location = location;
        this.distance = distance;
        this.price = price;
    }

    //GETTERS & SETTERS

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
