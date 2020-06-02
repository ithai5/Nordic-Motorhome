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
    private Double totalPrice;
    private double pricePerDay; //Needed for the totalContractPrice calculation + seasonPrice method calculation
    //Foreign keys
    private int customerId;
    private String licencePlate;
    private Integer pickId;
    private Integer dropId;
    //Missing Transfer table


    //CONSTRUCTORS
    public Contract() {}

    public Contract(int contractId, String startDate, String endDate, int startKm, Double totalPrice, int customerId, String licencePlate, Integer pickId, Integer dropId) {
        this.contractId = contractId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startKm = startKm;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.licencePlate = licencePlate;

        this.pickId = pickId;
        this.dropId = dropId;
    }

    //GETTERS AND SETTERS
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
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

    //Checks if start date of a contract is after its end date
    public boolean invalidDuration() {
        int yearS = extractDateTokenFromString(2, startDate);
        int monthS = extractDateTokenFromString(5, startDate);
        int dateS = extractDateTokenFromString(8, startDate);

        int yearE = extractDateTokenFromString(2, endDate);
        int monthE = extractDateTokenFromString(5, endDate);
        int dateE = extractDateTokenFromString(8, endDate);

        if (yearS <= yearE) {
            if (monthS == monthE) {
                if (dateS < dateE) {
                    return false;
                }
            }
            else if (monthS < monthE) {
                return false;
            }
        }
        return true;
    }

    //Converts a certain part of the String holding a Date into
    //their numeric values. Can be used for getting the year, month and day.
    public int extractDateTokenFromString(int index, String date) {
        int tokenA = Character.getNumericValue(date.charAt(0 + index)) * 10;
        int tokenB = Character.getNumericValue(date.charAt(1 + index)) * 1;
        int tokenC = tokenA + tokenB;

        return tokenC;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", startDate=" + startDate  +
                ", endDate=" + endDate  +
                ", startKm=" + startKm +
                ", totalPrice=" + totalPrice +
                ", customerId=" + customerId +
                ", licencePlate=" + licencePlate +
                ", pickId=" + pickId +
                ", dropId=" + dropId +
                '}';
    }
}
