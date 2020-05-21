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
    //Foreign keys
    private int customerId;
    private String licencePlate;
    private int contractGeneration; //timestamp needed to determine cancellation fees

    //CONSTRUCTORS

    public Contract() {}

    public Contract(int contractId, String startDate, String endDate, int startKm, double totalPrice, int customerId, String licencePlate) {
        this.contractId = contractId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startKm = startKm;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.licencePlate = licencePlate;
    }
}
