package com.example.demo.Repository;

import com.example.demo.Model.Contract;
import com.example.demo.Model.Customer;
import com.example.demo.Model.IdHolder;
import com.example.demo.Model.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public class IdHolderRepo {

    @Autowired
    JdbcTemplate template;

    public Contract findContractById(int contractId) {
        String sql = "SELECT * FROM KeaProject.Contract con " +
                "JOIN KeaProject.Customer cus ON con.customerId = cus.customerId " +
                "JOIN KeaProject.MhInfo inf ON con.licencePlate = inf.licencePlate " +
                "WHERE contractId = ?";

        RowMapper<Contract> contractRowmapper = new BeanPropertyRowMapper<>(Contract.class);
        return template.queryForObject(sql, contractRowmapper, contractId);
    }

    public Motorhome findMotorhomeByPlate(String licencePlate){
        String sql = "SELECT * FROM KeaProject.MhInfo i " +
                "JOIN KeaProject.MhSpecs s ON i.mhSpecsId = s.mhSpecsId " +
                "JOIN KeaProject.MhType t ON s.mhTypeId = t.mhTypeId " +
                "WHERE licencePlate = ?";
        RowMapper<Motorhome> motorhomeRowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.queryForObject(sql, motorhomeRowMapper, licencePlate);
    }

    public Customer findCustomerById(int customerId){
        String sql = "SELECT * " +
                "FROM KeaProject.Customer c " +
                "JOIN KeaProject.Address a ON a.addressId = c.addressId " +
                "WHERE customerId = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer customer = template.queryForObject(sql, rowMapper, customerId);
        return customer;
    }
    public IdHolder lastAddedToTable(String tableName){ //by inserting a string name of the table that you would like to find the last id that added to the table
        String sql = "SELECT * FROM KeaProject." + tableName;
        System.out.println(sql);
        //creating a new query to find the last id  that have been added to the database
        RowMapper<IdHolder> idHolderRowMapper = new BeanPropertyRowMapper<>(IdHolder.class);
        List<IdHolder> idHolderList = template.query(sql,idHolderRowMapper);
        return idHolderList.get(idHolderList.size()-1);//return the last id that have been added
    }

}
