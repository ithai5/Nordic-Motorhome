package com.example.demo.Repository;

import com.example.demo.Model.Contract;
import com.example.demo.Model.Motorhome;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//Made by Thomas
@Repository
public class ContractRepo extends IdHolderRepo {


    public List<Contract> fetchAll() {
        String sql = "SELECT * " +
                "FROM KeaProject.Contract";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.query(sql, rowMapper);
    }

    public void addContract(Contract contract, int customerId, String licencePlate) {
        String sql = "INSERT INTO KeaProject.Contract (contractId, startDate, endDate, startKm, endKm, totalPrice, customerId, licencePlate) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        //where is the totalPrice derived from? Nesrin
        template.update(sql, contract.getContractId(), contract.getStartDate(), contract.getEndDate(), contract.getStartKm(), contract.getTotalPrice(), customerId, licencePlate);
    }

    //Courtesy of Itai
    public List<Motorhome> availableCars(String startDate, String endDate) {
        String sql = "SELECT * FROM MhSpecs AS specs " +
                "JOIN " +
                "(SELECT MhInfo.licencePlate, startDate, endDate, contractId, MhInfo.mhSpecsId, MhInfo.odometer " +
                "FROM KeaProject.MhInfo AS info " + "JOIN KeaProject.Contract ON info.licencePlate= Contract.licencePlate " +
                "AND ((startDate >= '" + startDate + "' AND startDate <= '" + endDate + "') " + "OR(endDate >= '" + startDate + "' AND endDate <= '" + endDate + "') " +
                "OR (startDate >= '" + startDate + "' AND endDate <= '" + endDate + "')) " +
                "RIGHT JOIN MhInfo ON info.licencePlate = MhInfo.licencePlate " +
                "WHERE contractId IS NULL) AS C " + "ON specs.mhSpecsId = C.mhSpecsId  " +
                "JOIN KeaProject.MhType AS type " + "ON specs.mhTypeId = type.mhTypeId";

        return template.query(sql, new BeanPropertyRowMapper<>(Motorhome.class));
    }

    //Deleting a contract
    public void deleteContract(int contractId) {
        String sql = "DELETE FROM KeaProject.Contract " +
                "WHERE contractId = ?";
    }

    //Adapted by Thomas from Itais search method
    public List<Contract> searchForContract(String keyword) {
        String sql = "SELECT * FROM KeaProject.Contract " +
                "WHERE startDate LIKE '" + keyword + "%' " +
                "OR endDate LIKE '" + keyword + "%' " +
                "OR licencePlate = '" + keyword + "' ";
        RowMapper<Contract> contractRowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.query(sql, contractRowMapper);
    }


    public double amountOfDays (int contractId){

        String sql = "SELECT DATEDIFF(endDate, startDate) As totalPrice FROM KEAProject.Contract" +
        "WHERE contractId = " + contractId;
        RowMapper<Contract> contractRowMapper = new BeanPropertyRowMapper<>(Contract.class);
        List<Contract> contractList = template.query(sql, contractRowMapper);
        return contractList.get(0).getTotalPrice();
    }

    public double findPricePerDay (int contractId) {

        String sql = "SELECT pricePerDay  FROM Contract c " +
                "JOIN MhInfo i ON c.licencePlate = i.licencePlate " +
                "JOIN MhSpecs s ON i.mhSpecsId = s.mhSpecsId " +
                "JOIN MhType t ON s.mhTypeId =t.mhTypeId WHERE c.contractId = " + contractId;

        RowMapper<Motorhome> motorhomeRowMapper= new BeanPropertyRowMapper<>(Motorhome.class);
        List<Motorhome> motorhomeList = template.query(sql, motorhomeRowMapper);
        return motorhomeList.get(0).getPricePerDay();
    }

    public double seasonPricing (int contractId) {

        double peakSeason = 1.6;
        double midSeason = 1.3;
        double offSeason = 1;

        int[]month = {7, 8, 5, 10};

        for (int i=0; i<=2; i+=2){
            String sql = "SELECT * FROM KeaProject.Contract c " +
                    "JOIN MhInfo i ON c.licencePlate=i.licencePlate " +
                    "JOIN MhSpecs s ON i.mhSpecsId = s.mhSpecsId " +
                    "JOIN MhType t ON s.mhTypeId = t.mhTypeId " +
                    "WHERE MONTH(startDate) >= " + month[i] + /*" AND DAY(startDate)>= " + startPeakDay +*/
                    " AND MONTH(endDate) <= " + month[i+1] + /*" AND DAY(endDate)<= " + endPeakDay +*/
                    " AND contractId = " + contractId;

            RowMapper<Contract> contractRowMapper = new BeanPropertyRowMapper<>(Contract.class);
            List<Contract> contractList = template.query(sql, contractRowMapper);
            if (!contractList.isEmpty()&& i==0){
                return peakSeason;
            }else if (!contractList.isEmpty()&& i==2){
                return midSeason;
            }
        }
        return offSeason;
    }


    //finding totalPrice of Extras
    public double findExtraTotal(int contractId) {


        String sql = "SELECT SUM(pricePerDay*amount) AS totalPrice FROM KeaProject.Contract c " +
                "JOIN ContractHasExtra ce ON c.ContractId = ce.ContractId " +
                "JOIN Extra e ON ce.extraId = e.extraId " +
                "WHERE c.contractId = " + contractId;

        RowMapper<Contract> contractRowMapper = new BeanPropertyRowMapper<>(Contract.class);
        List<Contract> contractList = template.query(sql, contractRowMapper);

        if (contractList.isEmpty()){
            return 0;
        }

        return contractList.get(0).getTotalPrice();
    }

    //Possible transfer costs
    public double findTransferCosts (int contractId) {

        String sql = "SELECT  (t1.price + t2.price) totalPrice FROM Contract c " +
                "JOIN Transfer t1 ON c.pickId = t1.transferId " +
                "JOIN Transfer t2 ON c.dropId =  t2.transferId " +
                "WHERE c.contractId = " + contractId;

        RowMapper<Contract> contractRowMapper = new BeanPropertyRowMapper<>(Contract.class);
        List<Contract> contractList = template.query(sql, contractRowMapper);

        if (contractList.isEmpty()){
            return 0;
        }
        return contractList.get(0).getTotalPrice();
    }


    //Nesrin: simple method to add up all contract elements together
    public double completeContractTotal(int contractId) {
        double contractTotal = 0;
        contractTotal = amountOfDays(contractId)*findPricePerDay(contractId)*seasonPricing(contractId);
        contractTotal += findExtraTotal(contractId)*amountOfDays(contractId)+findTransferCosts(contractId);
        return contractTotal;
    }

    //Make a model version of invoice total
    //createModel Inovice
    //return invoicealso html


    /* Nesrin: method if customer reads summary and then chooses to abort process, leading to deletion of values
    in contract table */
    public void deleteProcess (int contractId) {
        //technically, the contract is made in the table at the time of reviewing summary
        //lastaddedtotheTable method (from IdHolderRepo) + deleteContract method
        //this shall not induce automatic cancellation fees
    }

    /*Nesrin: this method not only deals with contract expenses but other fees based on
    the behaviour of the customer */
    public double CompleteInvoiceTotal (int contractId) {

        double totalInvoice = 0;
      //  completeContractTotal + other fees like...
        /* if staff checkbox that customer leaves tank less than half full, + 70euros
    If customer has driven more than 400 km * 1euro
    Question: how do we handle the 400 free kilometers per day, insurance, external cleaning.
    Is this already inclusive in the daily amount? */
        return totalInvoice;
    }

    //Nesrin: after deletion of contract, this cancellation of contract should be called
    public double contractCancellation (int contractId){
        double fee = 0;
    /*
    If Up to 50 days prior to the start of the term of rental: 20% of the rental price, minimum 200€
    If Between 49 and 15 days prior to the start of the term of rental: 50% of the rental price
    If Less than 15 days prior to the start of the term of rental: 80% of the rental price
    If On the day of renting: 95% of the rental price */

    return fee;

    }



}
