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
        String sql = "SELECT typeName, pricePerDay, brand, model, seatNumber, bedNumber, licencePlate, odometer, ready, report " +
                "FROM KeaProject.MhSpecs AS a " +
                "JOIN KeaProject.MhInfo AS b ON a.mhSpecsId=b.mhSpecsId " +
                "JOIN KeaProject.MhType AS c ON a.mhTypeId=c.mhTypeId";
        RowMapper<Motorhome> rowMapper= new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql,rowMapper);
    }


    public Motorhome addMotorhome(Motorhome motorhome){
        String sql = "INSERT INTO KeaProject.MhInfo (licencePlate, odometer, ready, report, mhSpecsId, mhTypeId " +
                "VALUES (?, ?, ?, ?, ?)";
        template.update(sql, motorhome.getLicencePlate(), motorhome.getOdometer(), motorhome.isReady(), motorhome.getReport(), motorhome.getMhSpecsId(), motorhome.getMhTypeId());
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
        return idList.get(idList.size()-1).getMhTypeId();
    }

    public int addMhSpecs(Motorhome motorhome){
        String sql = "INSERT INTO KeaProject.MhSpecs (brand, model, seatNum, bedNum)" +
                "VALUES (?, ?, ?, ?)";
        template.update(sql, motorhome.getBrand(), motorhome.getModel(), motorhome.getSeatNum(), motorhome.getBedNum());
        sql = "SELECT mhSpecsId FROM KeaProject.MhSpecs";
        RowMapper<IdHolder> mhSpecsIds = new BeanPropertyRowMapper<>(IdHolder.class);
        List<IdHolder> idList = template.query(sql, mhSpecsIds);
        return idList.get(idList.size()-1).getMhSpecsId();
    }


    public List<Motorhome> searchMotorhome(String keyword){
        String sql = "SELECT * FROM KeaProject.MhType t " +
                "JOIN KeaProject.MhSpecs s ON t.mhTypeId = s.mhTypeId " +
                "JOIN KeaProject.MhInfo i ON s.mhSpecsId = i.mhSpecsId " +
                "WHERE licencePlate LIKE '" + keyword + "' " +
                "OR brand LIKE '" + keyword + "%' " +
                "OR model LIKE '" + keyword + "%' " +
                "OR seatNum LIKE '" + keyword + "' " +
                "OR bedNum LIKE '" + keyword + "' " +
                "OR typeName LIKE '" + keyword + "%' ";
        RowMapper<Motorhome> motorhomeRowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, motorhomeRowMapper);
    }

    public Motorhome findMotorhomeByPlate(String licencePlate){
        String sql = "SELECT * FROM KeaProject.MhInfo i " +
            "JOIN KeaProject.MhSpecs s ON i.mhSpecsId = s.mhSpecsId " +
            "JOIN KeaProject.MhType t ON s.mhTypeId = t.mhTypeId";
        RowMapper<Motorhome> motorhomeRowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        Motorhome motorhome = template.queryForObject(sql, motorhomeRowMapper, licencePlate);
        return motorhome;
    }

}
