package com.example.demo.Model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Motorhome {
    @Id
    //ATTRIBUTES
    //From MhType
    private Integer mhTypeId;
    private String typeName;
    private Double pricePerDay;
    //From MhSpecs
    private Integer mhSpecsId; // why is it on grey color?
    private String brand;
    private String model;
    private Integer seatNum;
    private Integer bedNum;
    //From MhInfo
    private String licencePlate;
    private Integer odometer;
    private Boolean state;
    private String report;


    //CONSTRUCTORS

    public Motorhome() {}

    public Motorhome (Integer mhTypeId, String typeName, Double pricePerDay, Integer mhSpecsId, String brand, String model, Integer seatNum, Integer bedNum, String licencePlate, Integer odometer, Boolean state, String report)

    {
        this.mhTypeId = mhTypeId;
        this.typeName = typeName;
        this.pricePerDay = pricePerDay;
        this.mhSpecsId = mhSpecsId;
        this.brand = brand;
        this.model = model;
        this.seatNum = seatNum;
        this.bedNum = bedNum;
        this.licencePlate = licencePlate;
        this.odometer = odometer;
        this.state = state;
        this.report = report;
    }

    //getters setters
    public Integer getMhTypeId(){
        return mhTypeId;
    }

    public void setMhTypeId(Integer mhTypeId) {
        this.mhTypeId = mhTypeId;
    }

    public String getTypeName ()
    {
        return typeName;
    }

    public void setTypeName (String typeName)
    {
        this.typeName = typeName;
    }

    public Double getPricePerDay ()
    {
        return pricePerDay;
    }

    public void setPricePerDay (Double pricePerDay)
    {
        this.pricePerDay = pricePerDay;
    }

    public Integer getMhSpecsId(){
        return mhSpecsId;
    }

    public void setMhSpecsId(Integer mhSpecsId) {
        this.mhSpecsId = mhSpecsId;
    }

    public String getBrand ()
    {
        return brand;
    }

    public void setBrand (String brand)
    {
        this.brand = brand;
    }

    public String getModel ()
    {
        return model;
    }

    public void setModel (String model)
    {
        this.model = model;
    }

    public Integer getSeatNum ()
    {
        return seatNum;
    }

    public void setSeatNum (Integer seatNum)
    {
        this.seatNum = seatNum;
    }

    public Integer getBedNum ()
    {
        return bedNum;
    }

    public void setBedNum (Integer bedNum)
    {
        this.bedNum = bedNum;
    }

    public String getLicencePlate ()
    {
        return licencePlate;
    }

    public void setLicencePlate (String licencePlate)
    {
        this.licencePlate = licencePlate;
    }

    public int getOdometer ()
    {
        return odometer;
    }

    public void setOdometer (Integer odometer)
    {
        this.odometer = odometer;
    }


    public Boolean isState ()
    {
        return state;
    }

    public void setState (Boolean status)
    {
        this.state = status;
    }

    public String getReport ()
    {
        return report;
    }

    public void setReport (String condition)
    {
        this.report = report;

    }

    @Override
    public String toString ()
    {
        return "Motorhome{" + "typeName='" + typeName + '\'' + ", pricePerDay=" + pricePerDay + ", brand='" + brand + '\'' + ", model='" + model + '\'' + ", seatNum=" + seatNum + ", bedNum=" + bedNum + ", licencePlate='" + licencePlate + '\'' + ", odometer=" + odometer + ", state=" + state + ", report='" + report + '\'' + '}';
    }
    //GETTERS & SETTERS

    //toString() method


}
