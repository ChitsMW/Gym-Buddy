package com.techelevator.dao;

import com.techelevator.model.Equipment;


import java.util.List;


public interface EquipmentDao {


    Equipment getEquipmentById(int equipmentId);

    List<Equipment> getAllEquipment();

    Equipment addNewEquipment(Equipment newEquipment);

    void deleteEquipment(int equipmentId);

    Equipment modifyEquipment(Equipment modifiedEquipment, int equipmentId);

}

