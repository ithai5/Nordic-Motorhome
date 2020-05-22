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

    public List<Motorhome> availableCars(String startDate, String endDate) {
        String sql = "SELECT * FROM MhSpecs AS b " +
                "JOIN " +
                "(SELECT CarInfo.licencePlate, startDate, endDate, rentalContract_id, CarInfo.specs_id, CarInfo.odometer, CarInfo.registration " +
                "FROM KeaProject.CarInfo AS a " + "JOIN KeaProject.RentalContract ON a.licencePlate= RentalContract.licencePlate " +
                "AND ((startDate >= '" + startDate + "' AND startDate <= '" + endDate + "') " + "OR(endDate >= '" + startDate + "' AND endDate <= '" + endDate + "') " +
                "OR (startDate >= '" + startDate + "' AND endDate <= '" + endDate + "')) " +
                "RIGHT JOIN CarInfo ON a.licencePlate = CarInfo.licencePlate " +
                "WHERE rentalContract_id IS NULL) AS c " + "ON b.specs_id = c.specs_id  " +
                "JOIN KeaProject.ClassType AS d " + "ON b.className_id = d.className_id";

        return template.query(sql,new BeanPropertyRowMapper<>(Motorhome.class));
    }
}
