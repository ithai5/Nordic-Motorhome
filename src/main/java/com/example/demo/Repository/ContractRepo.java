package com.example.demo.Repository;

import com.example.demo.Model.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//Made by Thomas
@Repository
public class ContractRepo extends IdHolderRepo {

    //Retrieves all elements from the contract table
    public List<Contract> fetchAllContract(){
        String sql = "SELECT * " +
                "FROM KeaProject.Contract";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.query(sql, rowMapper);
    }

    public void addContract(Contract contract){
        String sql = "INSERT INTO KeaProject.Contract (startDate, endDate, startKm, totalPrice, customerId, licencePlate, pickId, dropId) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        template.update(sql, contract.getStartDate(), contract.getEndDate(), contract.getStartKm(), contract.getTotalPrice(), contract.getCustomerId(), contract.getLicencePlate(), contract.getPickId(), contract.getDropId());
    }

    //Deleting a contract
    public void deleteContract(int contractId) {
        String sql = "DELETE FROM KeaProject.Contract " +
                "WHERE contractId = ?";

        template.update(sql, contractId);
    }

    public void deleteLastContract(){
        deleteContract(lastAddedToTable("Contract").getId());
    }

    //Adapted by Thomas from Itais search method
    //Only works for non-integer values (such as start and end date, and the licencePlate)
    //Consider adding linking this with searching for a customer?
    public List<Contract> searchForContract(String keyword){
        String sql = "SELECT * FROM KeaProject.Contract " +
                "WHERE startDate LIKE '" + keyword + "%' " +
                "OR endDate LIKE '" + keyword + "%' " +
                "OR licencePlate = '" + keyword + "' ";
        RowMapper<Contract> contractRowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.query(sql, contractRowMapper);
    }

    public List<Extra> fetchAllExtra(){
        String sql = "SELECT *" +
                "FROM KeaProject.Extra";
        RowMapper<Extra> rowMapper= new BeanPropertyRowMapper<>(Extra.class);
        return template.query(sql,rowMapper);
    }

    public void addExtrasToContract(List<Extra> extras) {
        String sql = "INSERT INTO KeaProject.ContractHasExtra (contractId, extraId, amount) " +
                "VALUES (?, ?, ?)";

        //Extracting the contractId from the newly added contract
        int contractId = lastAddedToTable("Contract").getId();
        //Getting the extraId from the values in the Extra table
        List<Extra> otherValues = fetchAllExtra();
        for (int i = 0; i < otherValues.size(); ++i) {
            extras.get(i).setExtraId(otherValues.get(i).getExtraId());
        }

        //Adding new entries to the ContractHasExtra table for each item that appears
        //more than 0 times
        for (Extra extra : extras) {
            if (extra.getAmount() != 0) {
                template.update(sql, contractId, extra.getExtraId(), extra.getAmount());
            }
        }
    }

    public void addPriceToContract() {
        double totalPrice= completeContractTotal(lastAddedToTable("Contract").getId()).getTotalContractPrice();
        int contractId=lastAddedToTable("Contract").getId();

        String sql = "UPDATE KeaProject.Contract " +
                "SET totalPrice = ?" +
                " WHERE contractId = ?";
        template.update(sql, totalPrice, contractId);

    }

    public void deleteExtrasFromContract(int contractId) {
        String sql = "DELETE FROM KeaProject.ContractHasExtra " +
                "WHERE contractId = ?";
        template.update(sql, contractId);
    }

    public void deleteExtrasFromLastContract() {
        String sql = "DELETE FROM KeaProject.ContractHasExtra " +
                "WHERE contractId = ?";
        int contractId = lastAddedToTable("Contract").getId();

        template.update(sql, contractId);
    }

    //Retrieves all elements from the transfer table
    public List<Transfer> fetchAllTransfer(){
        String sql = "SELECT *" +
                "FROM KeaProject.Transfer";
        RowMapper<Transfer> rowMapper= new BeanPropertyRowMapper<>(Transfer.class);
        return template.query(sql,rowMapper);
    }

    //Courtesy of Itai
    public List<Motorhome> availableMotorhomes(String startDate, String endDate) {
        String sql = "SELECT * FROM MhSpecs AS specs " +
                "JOIN " +
                "(SELECT MhInfo.licencePlate, startDate, endDate, contractId, MhInfo.mhSpecsId, MhInfo.odometer " +
                "FROM KeaProject.MhInfo AS info " + "JOIN KeaProject.Contract ON info.licencePlate= Contract.licencePlate " +
                "AND ((startDate >= '" + startDate + "' AND startDate <= '" + endDate + "') " + "OR(endDate >= '" + startDate + "' AND endDate <= '" + endDate + "') " +
                "OR (startDate >= '" + startDate + "' AND endDate <= '" + endDate + "')) " +
                "RIGHT JOIN MhInfo ON info.licencePlate = MhInfo.licencePlate " +
                "WHERE contractId IS NULL) AS C " + "ON specs.mhSpecsId = C.mhSpecsId  " +
                "JOIN KeaProject.MhType AS type " + "ON specs.mhTypeId = type.mhTypeId";

        return template.query(sql,new BeanPropertyRowMapper<>(Motorhome.class));
    }

    //Why does this return a double? A duration of days will always be an integer number
    public double amountOfDays (int contractId){

        String sql = "SELECT DATEDIFF(endDate, startDate) As totalPrice FROM KeaProject.Contract " +
        "WHERE contractId = " + contractId;
        RowMapper<Contract> contractRowMapper = new BeanPropertyRowMapper<>(Contract.class);
        List<Contract> contractList = template.query(sql, contractRowMapper);
        return contractList.get(0).getTotalPrice();
    }

    public double findPricePerDay (int contractId) {

        String sql = "SELECT pricePerDay  FROM KeaProject.Contract c " +
                "JOIN MhInfo i ON c.licencePlate = i.licencePlate " +
                "JOIN MhSpecs s ON i.mhSpecsId = s.mhSpecsId " +
                "JOIN MhType t ON s.mhTypeId =t.mhTypeId WHERE c.contractId = " + contractId;

        RowMapper<Motorhome> motorhomeRowMapper= new BeanPropertyRowMapper<>(Motorhome.class);
        List<Motorhome> motorhomeList = template.query(sql, motorhomeRowMapper);
        return motorhomeList.get(0).getPricePerDay();
    }

    public double findSeasonPricing (int contractId) {

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
                    " AND MONTH(startDate) <= " + month[i+1] + /*" AND DAY(endDate)<= " + endPeakDay +*/
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

        String sql = "SELECT  (t1.price + t2.price) totalPrice FROM KeaProject.Contract c " +
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
    public Invoice completeContractTotal(int contractId) {

        Invoice invoice = new Invoice();
        invoice.setSeasonPrice((findSeasonPricing(contractId)));
        invoice.setAmountDays((int)amountOfDays(contractId));
        invoice.setTypePriceTotal((findPricePerDay(contractId)));
        invoice.setExtraTotalPrice((findExtraTotal(contractId)));
        invoice.setTransferTotal((findTransferCosts(contractId)));
        invoice.setTotalContractPrice(invoice.findTotalContractPrice());
        
        return invoice;
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

    public double determineCancelModifier(int contractId) {
        String sql = "SELECT DATEDIFF(startDate, CURRENT_DATE) As id FROM KeaProject.Contract " +
                "WHERE contractId = " + contractId;
        RowMapper<IdHolder> idHolderRowMapper = new BeanPropertyRowMapper<>(IdHolder.class);
        int datesBeforeStart = template.query(sql, idHolderRowMapper).get(0).getId();

        if (datesBeforeStart > 50) {
            return 0.2;
        } else if (datesBeforeStart < 50 && datesBeforeStart > 14) {
            return 0.5;
        } else if (datesBeforeStart < 15) {
            return 0.8;
        } else if (datesBeforeStart == 0) {
            return 0.95;
        } else {
            return 1;
        }
    }

    //Currently no fuel check is done. Should an attribute ~isFull be added to the system
    //Or will we find a way to do it through the Report string?
    public double fuelAndKmCheck(Contract contract) {
        double priceAdditions = 0;

        //Calculating if the customer has driven more than
        //400 km a day
        double totalDays = amountOfDays(contract.getContractId());
        int newOdometer = findMotorhomeByPlate(contract.getLicencePlate()).getOdometer();
        double drivenDistance = newOdometer - contract.getStartKm();
        double kmsPerDay = drivenDistance / totalDays;

        if (kmsPerDay > 400) {
            priceAdditions = kmsPerDay - 400;
        }
        return priceAdditions;
    }

    //Changes the price accordingly in the event of a cancellation or
    //natural expiration of a contract, with too many kms driven
    public void generateEndPrice(Contract toEnd, boolean wasCanceled) {
        //Fee calculations for cancelling a contract
        if (wasCanceled) {
            double actualTotal = completeContractTotal(toEnd.getContractId()).getTotalContractPrice();
            //Check if price already has been modified and
            //if true, don't change the price
            if (toEnd.getTotalPrice() != actualTotal) {
                return;
            }
            double cancelModifier = determineCancelModifier(toEnd.getContractId());
            double newPrice = toEnd.getTotalPrice() * cancelModifier;
            if (newPrice < 200) {
                toEnd.setTotalPrice(200);
            } else {
                toEnd.setTotalPrice(newPrice);
            }
        }
        //Fee calculations for rents that have expired
        else {
            toEnd.setTotalPrice(toEnd.getTotalPrice() + fuelAndKmCheck(toEnd));
        }
        String sql = "UPDATE KeaProject.Contract " +
                "SET totalPrice = ?" +
                "WHERE contractId = ?";
        template.update(sql, toEnd.getTotalPrice(), toEnd.getContractId());
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
