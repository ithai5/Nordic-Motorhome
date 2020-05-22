package com.example.demo.Repository;

import com.example.demo.Model.Contract;
import com.example.demo.Model.Customer;
import com.example.demo.Model.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractRepo {

    @Autowired
    JdbcTemplate template;

    public List<Contract> fetchAll(){
        String sql = "SELECT * " +
                "FROM KeaProject.Contract";
        RowMapper<Contract> rowMapper= new BeanPropertyRowMapper<>(Contract.class);
        return template.query(sql,rowMapper);
    }

    public void addContract(Contract contract, int customerId, String licencePlate){
        String sql = "INSERT INTO KeaProject.Contract (contractId, startDate, endDate, startKm, endKm, totalPrice, customerId, licencePlate) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        template.update(sql, contract.getContractId(), contract.getStartDate(), contract.getEndDate(), contract.getStartKm(), contract.getTotalPrice(), customerId, licencePlate);
    }

    //Doesn't include state and report
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

        return template.query(sql,new BeanPropertyRowMapper<>(Motorhome.class));
    }

    //Deleting a contract
    public void deleteContract(int contractId){
        String sql = "DELETE FROM KeaProject.Contract " +
                "WHERE contractId = ?";
    }

    public List<Contract> searchForContract(String keyword){
        System.out.println("keyword = " + keyword);
        String sql = "SELECT * FROM KeaProject.Contract " +
                "WHERE startDate LIKE '" + keyword + "%' " +
                "OR endDate LIKE '" + keyword + "%' " +
                "OR customerId = " + keyword + " " +
                "OR licencePlate = '" + keyword + "' ";
        RowMapper<Contract> contractRowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.query(sql, contractRowMapper);
    }
}
