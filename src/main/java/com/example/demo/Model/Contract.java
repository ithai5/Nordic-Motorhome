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
    private int timestamp; //timestamp needed to determine cancellation fees
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

    //Checks if start date is after end date
    public boolean validDuration() {
        int yearS = extractDateTokenFromString(2, true);
        int monthS = extractDateTokenFromString(5, true);
        int dateS = extractDateTokenFromString(8, true);

        int yearE = extractDateTokenFromString(2, false);
        int monthE = extractDateTokenFromString(5, false);
        int dateE = extractDateTokenFromString(8, false);

        if (yearS <= yearE) {
            if (monthS <= monthE) {
                if (dateS < dateE) {
                    return true;
                }
            }
        }

        return false;
    }

    public int extractDateTokenFromString(int index, boolean isStart) {
        String dateType;
        if (isStart) {
            dateType = startDate;
        } else {
            dateType = endDate;
        }

        int token = 0;
        for (int i = 0; i < 2; ++i) {
            token += dateType.charAt(i + index);
        }
        return token;
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
                ", timestamp=" + timestamp +
                ", pickId=" + pickId +
                ", dropId=" + dropId +
                '}';
    }
}
