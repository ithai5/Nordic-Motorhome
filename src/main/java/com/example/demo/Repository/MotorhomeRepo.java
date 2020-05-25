package com.example.demo.Repository;

import com.example.demo.Model.IdHolder;
import com.example.demo.Model.Motorhome;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorhomeRepo extends IdHolderRepo {

    //VIEW ALL
    public List<Motorhome> fetchAll(){
        String sql = "SELECT typeName, pricePerDay, brand, model, seatNum, bedNum, licencePlate, odometer, ready, report " +
                "FROM KeaProject.MhSpecs AS a " +
                "JOIN KeaProject.MhInfo AS b ON a.mhSpecsId=b.mhSpecsId " +
                "JOIN KeaProject.MhType AS c ON a.mhTypeId=c.mhTypeId";
        RowMapper<Motorhome> rowMapper= new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql,rowMapper);
    }


    //ADD
    public Motorhome addMotorhome(Motorhome motorhome){
        String sql = "INSERT INTO KeaProject.MhInfo (licencePlate, odometer, ready, report, mhSpecsId) " +
                "VALUES (?, ?, ?, ?, ?)";
        int mhSpecsId = addMhSpecs(motorhome);
        template.update(sql, motorhome.getLicencePlate(), motorhome.getOdometer(), motorhome.isReady(), motorhome.getReport(), mhSpecsId);
        return motorhome;
    }

    public int addMhSpecs(Motorhome motorhome){
        String sql = "INSERT INTO KeaProject.MhSpecs (brand, model, seatNum, bedNum, mhTypeId) " +
                "VALUES (?, ?, ?, ?, ?)";
        int mhTypeId = addMhType(motorhome);
        template.update(sql, motorhome.getBrand(), motorhome.getModel(), motorhome.getSeatNum(), motorhome.getBedNum(), mhTypeId);
        return lastAddedToTable("MhSpecs").getId();
    }

    public int addMhType(Motorhome motorhome){
        String sql = "INSERT INTO KeaProject.MhType (typeName, pricePerDay) " +
                "VALUES (?, ?)";
        template.update(sql, motorhome.getTypeName(), motorhome.getPricePerDay());
        return lastAddedToTable("MhType").getId();
    }

    //SEARCH

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

    /*
    //findByPlate
    /* Moved inside idHolderRepo
    public Motorhome findMotorhomeByPlate(String licencePlate){
        String sql = "SELECT * FROM KeaProject.MhInfo i " +
            "JOIN KeaProject.MhSpecs s ON i.mhSpecsId = s.mhSpecsId " +
            "JOIN KeaProject.MhType t ON s.mhTypeId = t.mhTypeId " +
            "WHERE licencePlate = " + licencePlate;
        RowMapper<Motorhome> motorhomeRowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        Motorhome motorhome = template.queryForObject(sql, motorhomeRowMapper, licencePlate);
        return motorhome;
    }
    //DELETE
     */

    public boolean deleteMotorhome(String licencePlate){
        String sql = "DELETE FROM KeaProject.MhInfo WHERE licencePlate = ?";
        return template.update(sql, licencePlate)<0;
    }

    //UPDATE
    public Motorhome updateMotorhome(Motorhome motorhome)
    {
        String sql = "UPDATE KeaProject.MhInfo " + "SET odometer = ?, ready = ?, report = ? " + "WHERE licencePlate = ?";
        template.update(sql, motorhome.getOdometer(), motorhome.isReady(), motorhome.getReport(), motorhome.getLicencePlate());

        sql = "UPDATE KeaProject.MhSpecs " + "SET brand = ?, model = ?, seatNum = ?, bedNum = ? " + "WHERE mhSpecsId = ?";
        template.update(sql, motorhome.getBrand(), motorhome.getModel(), motorhome.getSeatNum(), motorhome.getBedNum(), motorhome.getMhSpecsId());

        sql = "UPDATE KeaProject.MhType " + "SET typeName = ?, pricePerDay = ? " + "WHERE mhTypeId = ?";
        template.update(sql, motorhome.getTypeName(), motorhome.getPricePerDay(), motorhome.getMhTypeId());
        return motorhome;
    }

}
