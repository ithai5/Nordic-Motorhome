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
public class IdHolderRepo {

    @Autowired
    JdbcTemplate template;
    /*
    //Maps a customer to the customer model. This will include a value for the address id,
    //but not the actual address values (city, country etc.)
    public List<Customer> searchCustomerById(int id) {
        String sql = "SELECT * FROM KeaProject.Customer WHERE Customer.customerId = " + id + ";";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql, rowMapper);
    }

    //Retrieves the address info by using the addressId, and maps it onto the customer model
    public List<Customer> searchAddressById(int id) {
        String sql = "SELECT * FROM KeaProject.Address WHERE Address.addressId = " + id + ";";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql, rowMapper);
    }
    */
    public Motorhome findMotorhomeByPlate(String licencePlate){
        String sql = "SELECT * FROM KeaProject.MhInfo i " +
                "JOIN KeaProject.MhSpecs s ON i.mhSpecsId = s.mhSpecsId " +
                "JOIN KeaProject.MhType t ON s.mhTypeId = t.mhTypeId " +
                "WHERE licencePlate = ?";
        RowMapper<Motorhome> motorhomeRowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        Motorhome motorhome = template.queryForObject(sql, motorhomeRowMapper, licencePlate);
        return motorhome;
    }

    Contract searchContractById() {
        return null;
    }

}
