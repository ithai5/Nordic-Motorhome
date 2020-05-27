package com.example.demo.Repository;

import com.example.demo.Model.Contract;
import com.example.demo.Model.Extra;
import com.example.demo.Model.Motorhome;
import com.example.demo.Model.Transfer;
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
        RowMapper<Contract> rowMapper= new BeanPropertyRowMapper<>(Contract.class);
        return template.query(sql,rowMapper);
    }

    public void addContract(Contract contract){
        String sql = "INSERT INTO KeaProject.Contract (startDate, endDate, startKm, totalPrice, customerId, licencePlate, pickId, dropId) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        template.update(sql, contract.getStartDate(), contract.getEndDate(), contract.getStartKm(), contract.getTotalPrice(), contract.getCustomerId(), contract.getLicencePlate(), contract.getPickId(), contract.getDropId());
    }

    //Deleting a contract
    public void deleteContract(int contractId){
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

    //Retrieves all elements from the extras table
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

        for (Extra extra : extras) {
            if (extra.getAmount() != 0) {
                template.update(sql, contractId, extra.getExtraId(), extra.getAmount());
            }
        }
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

    public double totalContractPrice(int contractId){
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
                     "WHERE contractId = " +contractId;
        RowMapper<Contract> contractRowMapper = new BeanPropertyRowMapper<>(Contract.class);
        List<Contract> contractList = template.query(sql, contractRowMapper);
        totalPrice = contractList.get(0).getTotalPrice();
        //Collecting the price of the transportation, can be split for another method
        sql = "SELECT  (t1.price + t2.price) totalPirce FROM Contract c " +
                "JOIN Transfer t1 ON c.pickId = t1.transferId " +
                "JOIN Transfer t2 ON c.dropId =  t2.transferId " +
                "WHERE c.contractId = " + contractId;
        contractList = template.query(sql,contractRowMapper);
        totalPrice += contractList.get(0).getTotalPrice();
        System.out.println(totalPrice);
        return totalPrice;
    }
}
