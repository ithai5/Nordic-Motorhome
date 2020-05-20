package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import com.example.demo.Model.IdHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepo {
    @Autowired
    JdbcTemplate template;

    public List<Customer> fetchAll(){
        String sql = "SELECT * " +
                "FROM KeaProject.Customer";
        RowMapper<Customer> rowMapper= new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql,rowMapper);
    }

    public Customer addCustomer(Customer customer){
        String sql = "INSERT INTO KeaProject.Customer (firstName, lastName, email, driverNum, phone, addressId) " +
                "VALUES (?,?,?,?,?,?)";
        int addressId = createAddress(customer);
        template.update(sql,customer.getFirstName(),customer.getLastName(),customer.getEmail(), customer.getDriverNum(),customer.getPhone(),addressId);
        return null;
    }

    public int createAddress(Customer customer){
        String sql = "INSERT INTO KeaProject.Address (country, city, street, houseNum, zip) " +
                "VALUES (?,?,?,?,?)";
        template.update(sql,customer.getCountry(),customer.getCity(),customer.getStreet(),customer.getHouseNum(),customer.getZip());
        //break point for method for axstract the last added id in a table
        sql = "SELECT addressId FROM KeaProject.Address ";
        RowMapper<IdHolder> addressIds = new BeanPropertyRowMapper<>(IdHolder.class);//getting a list of all the addressId
        List<IdHolder> idList = template.query(sql,addressIds); //sign in the row mapper list into an integer list
        return idList.get(idList.size()-1).getAddressId(); //getting the last value that has been added to the list

    }

    public List<Customer> searchForCustomer(String keyword){
        String sql = "SELECT * FROM Customer c " + "JOIN Address a ON c.addressId=a.addressId " +
                "WHERE c.firstName LIKE '%" + keyword + "%' " +
                "OR c.lastName LIKE '%" + keyword + "%' " +
                "OR c.email LIKE '%" + keyword + "%' " +
                "OR phone LIKE '%" + keyword + "%' ";
        RowMapper<Customer> customerRowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql,customerRowMapper);
    }

    /*
    public Customer findCustomerById(int customer_id){
        String sql = "SELECT * " +
                "FROM KeaProject.Customer " +
                "WHERE customer_id = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer customer = template.queryForObject(sql, rowMapper, customer_id);
        return customer;
    }

    public Boolean deleteCustomer(int customerId){
        String sql = "DELETE FROM KeaProject.Customer " +
                "WHERE customer_id = ?";
        return template.update(sql,customerId)<0;
    }

    public Customer updateCustomer(Customer customer){
        String sql = "UPDATE KeaProject.Customer " +
                "SET firstName = ?, lastName = ?, email = ? " +
                "WHERE customer_id = ?";
        template.update(sql, customer.getFirstName(),customer.getLastName(),customer.getEmail(), customer.getCustomer_id());
        return customer;
    }

     */
}
