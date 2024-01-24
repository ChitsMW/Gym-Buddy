package com.techelevator.controller;
import com.techelevator.dao.EquipmentDao;
import com.techelevator.dao.ExerciseLogDao;
import com.techelevator.model.ExerciseLog;
import com.techelevator.model.ExerciseLogDto;
import com.techelevator.model.LogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @RequestMapping(path="/gym/{sessionId}", method=RequestMethod.GET)
    public ExerciseLogDto getExerciseLogBySessionId(@PathVariable int sessionId){
        return exerciseLogDao.getExerciseLogBySessionId(sessionId);
    }


    @RequestMapping(path = "/list/{userId}" , method = RequestMethod.GET)
    public List<LogDto> getExerciseLogByUserId(@PathVariable int userId){
        return exerciseLogDao.getExerciseLogByUserId(userId);
    }
}
