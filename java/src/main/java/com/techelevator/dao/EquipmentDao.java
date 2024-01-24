package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.LogDto;


import java.util.List;


public interface EquipmentDao {


    Equipment getEquipmentById(int equipmentId);

    List<Equipment> getAllEquipment();

    Equipment addNewEquipment(Equipment newEquipment);

    void deleteEquipment(int equipmentId);

    Equipment modifyEquipment(Equipment modifiedEquipment, int equipmentId);
    int getRepsFromEquipment(int equipmentId);


    Equipment addReps(int reps, int equipmentId);

    List <LogDto> getMonthlyUsage(String year, String month);

}

