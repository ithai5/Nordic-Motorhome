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


    //additions by Nesrin on Itai's method : This method should focus on the TYPE of price determined by
    //the type of motorhome, and the peak season. No other parametres needed at this stage as it simply
    //determines the pricePerDay
    public double totalContractPrice(int contractId) {
        double totalPrice = 0;
        //creating an sql for find the price for the contract exclude extra
        String sql = "SELECT contractId, startDate, endDate, customerId, " +
                "(pricePerDay+(SELECT SUM(amount*pricePerDay) AS PriceForExtra " + //collect the price of the extra per day
                "FROM KeaProject.ContractHasExtra h " +
                "JOIN Extra e ON h.extraId = e.extraId " +
                "WHERE contractId = " + contractId + ")) *DATEDIFF(endDate,startDate) AS totalPrice " +
                //calculate the amount of days that the contract is and time it the price per day of the motorhome
                "FROM Contract c " +
                "JOIN MhInfo i ON c.licencePlate=i.licencePlate " +
                "JOIN MhSpecs s ON i.mhSpecsId = s.mhSpecsId " +
                "JOIN MhType t ON s.mhTypeId = t.mhTypeId " + //getting the price per day form the table MhType
                "WHERE contractId = " + contractId;
        RowMapper<Contract> contractRowMapper = new BeanPropertyRowMapper<>(Contract.class);
        List<Contract> contractList = template.query(sql, contractRowMapper);
        totalPrice = contractList.get(0).getTotalPrice();
        contractList = template.query(sql, contractRowMapper);
        totalPrice += contractList.get(0).getTotalPrice();
        System.out.println(totalPrice);
        return totalPrice;
    }

    //Notes by Nesrin
    //
    // determine the peak prices
    //if statement:
    /*difference between start date and end date (can change due to peak season, middle, low season)
    include if statement (if date is between peak start and peak end)
        then price per day * 60%
    include if statement (if date is between middle start and middle end) + 30%
        then price per day * 30%
   else normal priceperday
    then */


    //split into another method by Nesrin
        public double calculateExtras(int contractId){

        double extraPrice=0;
        //include if customer choses from extras Table

        String sql = "SELECT  (t1.price + t2.price) totalPirce FROM Contract c " +
                "JOIN Transfer t1 ON c.pickId = t1.transferId " +
                "JOIN Transfer t2 ON c.dropId =  t2.transferId " +
                "WHERE c.contractId = " + contractId;
        RowMapper<Contract> contractRowMapper = new BeanPropertyRowMapper<>(Contract.class);
        List<Contract> contractList = template.query(sql, contractRowMapper);
        extraPrice = contractList.get(0).getTotalPrice();
        contractList = template.query(sql, contractRowMapper);
        extraPrice += contractList.get(0).getTotalPrice();
        System.out.println(extraPrice);
        return extraPrice;
    }


    //technically, the contract is made in the table at the time of summary
    //If customer choses cancel rental process, then call delete contract method to clear the row
    //this shall not induce automatic cancellation fees

    //Need to remember (by Nesrin)
    //public double totalInvoice (int contractId){

    /* Include general invoice (including contract + others fees):
    If statement, if staff checkbox that customer leaves tank less than half full, + 70euros
    If customer has driven more than 400 km * 1euro
    Question: how do we handle the 400 free kilometers per day, insurance, external cleaning.
    Is this already inclusive in the daily amount?

    If statements regarding cancellation:
    If Up to 50 days prior to the start of the term of rental: 20% of the rental price, minimum 200€
    If Between 49 and 15 days prior to the start of the term of rental: 50% of the rental price
    If Less than 15 days prior to the start of the term of rental: 80% of the rental price
    If On the day of renting: 95% of the rental price
    }*/



}
