package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contract {
    @Id
    //ATTRIBUTES
    private int contractId;
    private String startDate;
    private String endDate;
    private int startKm;
    private double totalPrice;
    private double pricePerDay; //Needed for the totalContractPrice calculation + seasonPrice method calculation
    //Foreign keys
    private int customerId;
    private String licencePlate;
    private Integer pickId;
    private Integer dropId;
    //Missing Transfer table


    //CONSTRUCTORS

    public Contract() {}

    public Contract(int contractId, String startDate, String endDate, int startKm, double totalPrice, int customerId, String licencePlate, int timestamp, Integer pickId, Integer dropId) {
        this.contractId = contractId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startKm = startKm;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.licencePlate = licencePlate;
        this.timestamp = timestamp;
        this.pickId = pickId;
        this.dropId = dropId;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getStartKm() {
        return startKm;
    }

    public void setStartKm(int startKm) {
        this.startKm = startKm;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getPickId() {
        return pickId;
    }

    public void setPickId(Integer pickId) {
        this.pickId = pickId;
    }

    public Integer getDropId() {
        return dropId;
    }

    public void setDropId(Integer dropId) {
        this.dropId = dropId;
    }

    //toString()

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startKm=" + startKm +
                ", totalPrice=" + totalPrice +
                ", customerId=" + customerId +
                ", licencePlate='" + licencePlate + '\'' +
                ", timestamp=" + timestamp +
                ", pickId=" + pickId +
                ", dropId=" + dropId +
                '}';
    }
}
