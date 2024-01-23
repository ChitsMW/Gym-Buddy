package com.techelevator.controller;
import com.techelevator.dao.EquipmentDao;
import com.techelevator.dao.ExerciseLogDao;
import com.techelevator.model.ExerciseLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RequestMapping("/log")
@RestController
public class ExerciseLogController {


    @Autowired
    private ExerciseLogDao exerciseLogDao;


    @RequestMapping(path="/newExercise", method = RequestMethod.POST)
    public ExerciseLog addNewExerciseLog(@RequestBody ExerciseLog exerciseLog){
        return exerciseLogDao.addNewExerciseLog(exerciseLog);
    }


    @RequestMapping(path="/{logId}", method=RequestMethod.GET)
    public ExerciseLog getExerciseLogById(@PathVariable int logId){
        return exerciseLogDao.getExerciseLogById(logId);
    }
}
