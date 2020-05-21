package com.example.demo.Repository;

import com.example.demo.Model.Customer;
import com.example.demo.Model.IdHolder;
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
     */

    public Motorhome addMotorhome(Motorhome motorhome){
        String sql = "INSERT INTO KeaProject.MhInfo (licencePlate, odometer, state, report, mhSpecsId, mhTypeId") +
                "VALUES (?, ?, ?, ?, ?)";
        template.update(sql, motorhome.getLicencePlate(), motorhome.getOdometer(), motorhome........, motorhome.getReport(), motorhome.getMhSpecsId(), motorhome.getMhTypeId());
        // missing to include "state" (boolean) in the template!
        return null;
    }

    public int addMhType(Motorhome motorhome){
        String sql = "INSERT INTO KeaProject.MhType (typeName, pricePerDay)" +
                "VALUES (?, ?)";
        template.update(sql, motorhome.getTypeName(), motorhome.getPricePerDay());
        sql = "SELECT mhTypeId FROM KeaProject.MhType";
        //now we need to find the last mhTypeId added in the table
        RowMapper<IdHolder> mhTypeIds = new BeanPropertyRowMapper<>(IdHolder.class);
        List<IdHolder> idList = template.query(sql, mhTypeIds);
        return idList.get(idList.size()-1).getMhTypeId(); // doesnt work!
    }

    public int addMhSpecs(Motorhome motorhome){
        String sql = "INSERT INTO KeaProject.MhSpecs (brand, model, seatNum, bedNum)" +
                "VALUES (?, ?, ?, ?)";
        template.update(sql, motorhome.getBrand(), motorhome.getModel(), motorhome.getSeatNum(), motorhome.getBedNum());
        sql = "SELECT mhSpecsId FROM KeaProject.MhSpecs";
        RowMapper<IdHolder> mhSpecsIds = new BeanPropertyRowMapper<>(IdHolder.class);
        List<IdHolder> idList = template.query(sql, mhSpecsIds);
        return idList.get(idList.size()-1).getMhSpecsId(); // doesnt work!
    }
}
