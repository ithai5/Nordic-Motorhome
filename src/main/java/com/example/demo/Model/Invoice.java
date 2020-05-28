package com.example.demo.Model;

public class Invoice  {

    private double seasonPrice;
    private int amountDays;
    private double typePriceTotal;
    private double extraTotalPrice;
    private double transferTotal;
    private double totalContractPrice;

    public double getTransferTotal() {
        return transferTotal;
    }

    public void setTransferTotal(double transferTotal) {
        this.transferTotal = transferTotal;
    }

    public Invoice(double seasonPrice, int amountDays, double typePriceTotal, double extraTotalPrice, double transferTotal, double totalContractPrice) {
        this.seasonPrice = seasonPrice;
        this.amountDays = amountDays;
        this.typePriceTotal = typePriceTotal;
        this.extraTotalPrice = extraTotalPrice;
        this.transferTotal = transferTotal;
        this.totalContractPrice = totalContractPrice;
    }

    public Invoice() {
    }

    public double getSeasonPrice() {
        return seasonPrice;
    }

    public void setSeasonPrice(double seasonPrice) {
        this.seasonPrice = seasonPrice;
    }

    public int getAmountDays() {
        return amountDays;
    }


    public void setAmountDays(int amountDays) {
        this.amountDays = amountDays;
    }

    public double getTypePriceTotal() {
        return typePriceTotal;
    }

    public void setTypePriceTotal(double typePriceTotal) {
        this.typePriceTotal = typePriceTotal;
    }

    public double getExtraTotalPrice() {
        return extraTotalPrice;
    }

    public void setExtraTotalPrice(double extraTotalPrice) {
        this.extraTotalPrice = extraTotalPrice;
    }

    public void setTotalContractPrice(double totalContractPrice) {
        this.totalContractPrice = totalContractPrice;
    }

    public double getTotalContractPrice() {
        return totalContractPrice;
    }

    public double findTotalContractPrice (){

        return this.amountDays*this.typePriceTotal*this.seasonPrice +
                (this.extraTotalPrice*this.amountDays+this.transferTotal);

    }

    @Override
    public String toString() {
        return "Invoice{" +
                "seasonPrice=" + seasonPrice +
                ", amountDays=" + amountDays +
                ", typePriceTotal=" + typePriceTotal +
                ", extraTotalPrice=" + extraTotalPrice +
                ", transferTotal=" + transferTotal +
                ", totalContractPrice=" + totalContractPrice +
                '}';
    }


}
