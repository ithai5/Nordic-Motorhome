package com.example.demo.Model;

<<<<<<< HEAD

=======
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
>>>>>>> master
public class Motorhome {
    @Id
    //ATTRIBUTES
    //From MhType
    private String typeName;
    private Double pricePerDay;
    //From MhSpecs
    private String brand;
    private String model;
    private Integer seatNum;
    private Integer bedNum;
    //From MhInfo
    private String licencePlate;
    private Integer odometer;
<<<<<<< HEAD
    private Boolean status;
    private String condition;
=======
    private Boolean state;
    private String report;
>>>>>>> master

    //CONSTRUCTORS

    public Motorhome() {}

<<<<<<< HEAD
    public Motorhome (String typeName, Double pricePerDay, String brand, String model, Integer seatNum, Integer bedNum, String licencePlate, Integer odometer, Boolean status, String condition)
=======
    public Motorhome (String typeName, Double pricePerDay, String brand, String model, Integer seatNum, Integer bedNum, String licencePlate, Integer odometer, Boolean state, String report)
>>>>>>> master
    {
        this.typeName = typeName;
        this.pricePerDay = pricePerDay;
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

<<<<<<< HEAD
    public Boolean isStatus ()
    {
        return status;
    }

    public void setStatus (Boolean status)
    {
        this.status = status;
    }

    public String getCondition ()
    {
        return condition;
    }

    public void setCondition (String condition)
    {
        this.condition = condition;
=======
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
>>>>>>> master
    }

    @Override
    public String toString ()
    {
        return "Motorhome{" + "typeName='" + typeName + '\'' + ", pricePerDay=" + pricePerDay + ", brand='" + brand + '\'' + ", model='" + model + '\'' + ", seatNum=" + seatNum + ", bedNum=" + bedNum + ", licencePlate='" + licencePlate + '\'' + ", odometer=" + odometer + ", status=" + status + ", condition='" + condition + '\'' + '}';
    }
    //GETTERS & SETTERS

    //toString() method


}
