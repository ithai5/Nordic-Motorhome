package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorhomeRepo {
    @Autowired
    JdbcTemplate template;

    public List<Motorhome> fetchAll(){
        String sql = "SELECT typeName, pricePerDay, brand, model, seatNumber,bedNumber, licencePlate,odometer, state, report " +
                "FROM KeaProject.MhSpecs AS a " +
                "JOIN KeaProject.MhInfo AS b ON a.mhSpecsId=b.mhSpecsId " +
                "JOIN KeaProject.MhType AS c ON a.mhTypeId=c.mhTypeId";
        RowMapper<Motorhome> rowMapper= new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql,rowMapper);
    }

    /*
    public Customer addCustomer(Customer customer){
        String sql = "INSERT INTO KeaProject.Motorhome (firstName, lastName, email, driverNum, phone, addressId) " +
                "VALUES (?,?,?,?,?,?)";
        template.update(sql,customer.getFirstName(),customer.getLastName(),customer.getEmail(), customer.getDriverNum(),customer.getPhone(),addressId);
        return null;
    }

     */

}
