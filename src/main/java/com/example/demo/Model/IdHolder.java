package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IdHolder {
    @Id
    private Integer employeeId;
    private Integer customerId;
    private Integer addressId;
    private Integer mhTypeId;
    private Integer mhSpecsId;
    private String licencePlate;
    private Integer contractId;
    private Integer transferId;
    private Integer extraId;

    //CONSTRUCTORS

    public IdHolder ()
    {
    }

    //GETTERS & SETTERS

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getMhTypeId() {
        return mhTypeId;
    }

    public void setMhTypeId(Integer mhTypeId) {
        this.mhTypeId = mhTypeId;
    }

    public Integer getMhSpecsId() {
        return mhSpecsId;
    }

    public void setMhSpecsId(Integer mhSpecsId) {
        this.mhSpecsId = mhSpecsId;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public Integer getExtraId() {
        return extraId;
    }

    public void setExtraId(Integer extraId) {
        this.extraId = extraId;
    }

    //toString()

    @Override
    public String toString() {
        return "IdHolder{" +
                "employeeId=" + employeeId +
                ", customerId=" + customerId +
                ", addressId=" + addressId +
                ", mhTypeId=" + mhTypeId +
                ", mhSpecsId=" + mhSpecsId +
                ", licencePlate='" + licencePlate + '\'' +
                ", contractId=" + contractId +
                ", transferId=" + transferId +
                ", extraId=" + extraId +
                '}';
    }
}
