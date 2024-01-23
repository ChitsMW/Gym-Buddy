package com.techelevator.controller;


import com.techelevator.dao.EquipmentDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;


@CrossOrigin
@RequestMapping("/equipment")
@RestController
public class EquipmentController {


    @Autowired
    private EquipmentDao equipmentDao;


    @RequestMapping(path = "/{equipmentId}", method = RequestMethod.GET)
    public Equipment getEquipmentById(@PathVariable int equipmentId) {
        return equipmentDao.getEquipmentById(equipmentId);
    }


//    @RequestMapping(path = "/list", method = RequestMethod.GET)
//    public List<Equipment> getAllEquipment() {
//        return equipmentDao.getAllEquipment();
//    }
@RequestMapping(path = "/list", method = RequestMethod.GET)


public List<Equipment> getAllEquipment() {
    try {
        return equipmentDao.getAllEquipment();
    }catch (DaoException e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving equipment.");
    }
}



    @RequestMapping(path = "/{equipmentId}", method = RequestMethod.POST)
    public Equipment addNewEquipment(@RequestBody Equipment equipment) {
        return equipmentDao.addNewEquipment(equipment);
    }


    @RequestMapping(path = "/{equipmentId}", method = RequestMethod.DELETE)
    public void deleteRecipe(@PathVariable int equipmentId) {
        equipmentDao.deleteEquipment(equipmentId);
    }


    @RequestMapping(path = "/{equipmentId}", method = RequestMethod.PUT)
    public Equipment modifyEquipment(@PathVariable int equipmentId, @RequestBody Equipment equipment) {
        return equipmentDao.modifyEquipment(equipment, equipmentId);
    }
    @RequestMapping(path = "/totalReps/{equipmentId}", method = RequestMethod.GET)
    public int getRepsFromEquipment(@PathVariable int equipmentId) {
        return equipmentDao.getRepsFromEquipment(equipmentId);
    }


    @RequestMapping(path = "/addReps/{equipmentId}", method = RequestMethod.PUT)
    public Equipment addReps(@PathVariable int equipmentId, @RequestBody int reps) {
        return equipmentDao.addReps(reps, equipmentId);
    }



}
