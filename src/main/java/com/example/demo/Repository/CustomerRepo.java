package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import com.example.demo.Model.IdHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepo {
    @Autowired
    JdbcTemplate template;

    //collect all the information about customers
    public List<Customer> fetchAll(){
        String sql = "SELECT * " +
                "FROM KeaProject.Customer";
        RowMapper<Customer> rowMapper= new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql,rowMapper);
    }

    //creating a query for adding a new customer
    public Customer addCustomer(Customer customer){
        String sql = "INSERT INTO KeaProject.Customer (firstName, lastName, email, driverNum, phone, addressId) " +
                "VALUES (?,?,?,?,?,?)";
        int addressId = createAddress(customer); //creating a new address in the database and get the id
        template.update(sql,customer.getFirstName(),customer.getLastName(),customer.getEmail(), customer.getDriverNum(),customer.getPhone(),addressId);
        return null;
    }

    public int createAddress(Customer customer){
        String sql = "INSERT INTO KeaProject.Address (country, city, street, houseNum, zip) " +
                "VALUES (?,?,?,?,?)";
        template.update(sql,customer.getCountry(),customer.getCity(),customer.getStreet(),customer.getHouseNum(),customer.getZip());
        //takes the informaion form the cusotmer object and sign it in to the address table in the database

        //break point for method for axstract the last added id in a table
        sql = "SELECT addressId FROM KeaProject.Address ";
        RowMapper<IdHolder> addressIds = new BeanPropertyRowMapper<>(IdHolder.class);//getting a list of all the addressId
        List<IdHolder> idList = template.query(sql,addressIds); //sign in the row mapper list into an integer list
        return idList.get(idList.size()-1).getAddressId(); //getting the last value that has been added to the list

    }

    public List<Customer> searchForCustomer(String keyword){
        String sql = "SELECT * FROM KeaProject.Customer c " + "JOIN KeaProject.Address a ON c.addressId = a.addressId " +
                "WHERE firstName LIKE '" + keyword + "%' " +
                "OR lastName LIKE '" + keyword + "%' " +
                "OR email LIKE '" + keyword + "' " +
                "OR phone LIKE '" + keyword + "' ";//collect information form both tables
        RowMapper<Customer> customerRowMapper = new BeanPropertyRowMapper<>(Customer.class);//model the tables into the customer object list
        return template.query(sql,customerRowMapper);
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

    public Boolean deleteCustomer(int customerId){
        String sql = "DELETE FROM KeaProject.Customer " +
                "WHERE customerId = ?";
        return template.update(sql,customerId)<0;
    }

    public Customer updateCustomer(Customer customer){
        String sql = "UPDATE KeaProject.Customer " +
                "SET firstName = ?, lastName = ?, email = ?, phone = ?, driverNum = ? " +
                "WHERE customerId = ?";
        template.update(sql, customer.getFirstName(),customer.getLastName(),customer.getEmail(),customer.getPhone(),customer.getDriverNum(), customer.getCustomerId()); //update the information into the customer table in the database

        sql = "UPDATE KeaProject.Address " +
                "SET country = ?, city = ?, street = ?, houseNum = ?, zip = ? " +
                "WHERE addressId = ?";
        template.update(sql, customer.getCountry(),customer.getCity(), customer.getStreet(), customer.getHouseNum(), customer.getZip(),customer.getAddressId());//update the information into the address table in the database
        return customer;
    }


}
