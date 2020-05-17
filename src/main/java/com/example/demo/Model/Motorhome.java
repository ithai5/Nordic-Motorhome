package com.example.demo.Model;

public class Motorhome {
    //ATTRIBUTES
    //From MhType
    private String typeName;
    private double pricePerDay;
    //From MhSpecs
    private String brand;
    private String model;
    private int seatsNum;
    private int bedsNUm;
    //From MhInfo
    private String licencePlate;
    private int odometer;
    private boolean status;
    private String condition;

    //CONSTRUCTORS

    public Motorhome() {}

    public Motorhome(String typeName, double pricePerDay, String brand, String model, int seatsNum, int bedsNUm, String licencePlate, int odometer, boolean status, String condition) {
        this.typeName = typeName;
        this.pricePerDay = pricePerDay;
        this.brand = brand;
        this.model = model;
        this.seatsNum = seatsNum;
        this.bedsNUm = bedsNUm;
        this.licencePlate = licencePlate;
        this.odometer = odometer;
        this.status = status;
        this.condition = condition;
    }

    //GETTERS & SETTERS

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeatsNum() {
        return seatsNum;
    }

    public void setSeatsNum(int seatsNum) {
        this.seatsNum = seatsNum;
    }

    public int getBedsNUm() {
        return bedsNUm;
    }

    public void setBedsNUm(int bedsNUm) {
        this.bedsNUm = bedsNUm;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    //toString() method

    @Override
    public String toString() {
        return "Motorhome{" +
                "typeName='" + typeName + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", seatsNum=" + seatsNum +
                ", bedsNUm=" + bedsNUm +
                ", licencePlate='" + licencePlate + '\'' +
                ", odometer=" + odometer +
                ", status=" + status +
                ", condition='" + condition + '\'' +
                '}';
    }
}
