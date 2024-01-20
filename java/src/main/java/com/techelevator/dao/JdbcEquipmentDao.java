package com.techelevator.dao;


import com.techelevator.model.Equipment;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEquipmentDao implements EquipmentDao {


    private JdbcTemplate jdbcTemplate;


    public JdbcEquipmentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Equipment getEquipmentById(int equipmentId) {
        Equipment equipment = null;


        String sql = "SELECT equipment_id, total_reps, equipment_name, equipment_type, equipment_photo, instructions_video, instructions_animation, targeted_area" +
                "FROM equipment" +
                "WHERE equipment_id = ?;";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, equipmentId);
        while (row.next()) {
            equipment = mapRowsetToEquipment(row);
        }
        return equipment;
    }


    @Override
    public List<Equipment> getAllEquipment() {
        List<Equipment> allEquipment = new ArrayList<>();
        String sql = "SELECT * from equipment";


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Equipment equipment = mapRowsetToEquipment(results);
            allEquipment.add(equipment);
        }


        return allEquipment;
    }


    @Override
    public Equipment addNewEquipment(Equipment newEquipment) {
        Equipment addedEquipment = null;
        String sql = "INSERT INTO equipment (total_reps,equipment_name, equipment_type, equipment_photo, instructions_video, instructions_animation, targeted_area) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING equipment_id;";


        int newEquipmentId = jdbcTemplate.queryForObject(sql, int.class, newEquipment.getTotalReps(), newEquipment.getEquipmentName(),
                newEquipment.getEquipmentType(), newEquipment.getEquipmentPhoto(), newEquipment.getInstructionsVideo(), newEquipment.getInstructionsAnimation(), newEquipment.getTargetedArea());
        addedEquipment = getEquipmentById(newEquipmentId);


        return addedEquipment;
    }


    @Override
    public void deleteEquipment(int equipmentId) {
        String sql = "DELETE FROM equipment WHERE equipment_id = ?";
        jdbcTemplate.update(sql, equipmentId);


    }


    @Override
    public Equipment modifyEquipment(Equipment modifiedEquipment, int equipmentId) {
        Equipment changedEquipment = null;
        String sql = "UPDATE equipment SET total_reps=?, equipment_name=?, equipment_type=?, equipment_photo=?,instructions_video=?, instructions_animation=?,targeted_area=? WHERE equipment_id = ?;";


        jdbcTemplate.update(sql, modifiedEquipment.getTotalReps(), modifiedEquipment.getEquipmentName(), modifiedEquipment.getEquipmentName(), modifiedEquipment.getEquipmentPhoto(), modifiedEquipment.getInstructionsVideo(), modifiedEquipment.getInstructionsAnimation(), modifiedEquipment.getTargetedArea(), equipmentId);
        changedEquipment = getEquipmentById(equipmentId);


        return changedEquipment;
    }


    public Equipment mapRowsetToEquipment(SqlRowSet row) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(row.getInt("equipment_id"));
        equipment.setTotalReps(row.getInt("total_reps"));
        equipment.setEquipmentName(row.getString("equipment_name"));
        equipment.setEquipmentType(row.getString("equipment_type"));
        equipment.setEquipmentPhoto(row.getString("equipment_photo"));
        equipment.setInstructionsVideo(row.getString("instructions_video"));
        equipment.setInstructionsAnimation(row.getString("instructions_animation"));
        equipment.setTargetedArea(row.getString("targeted_area"));
        return equipment;
    }
}

