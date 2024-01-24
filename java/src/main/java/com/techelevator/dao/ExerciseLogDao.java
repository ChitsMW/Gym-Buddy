package com.techelevator.dao;
import com.techelevator.model.Equipment;
import com.techelevator.model.ExerciseLog;
import com.techelevator.model.ExerciseLogDto;
import com.techelevator.model.LogDto;

import java.util.List;

public interface ExerciseLogDao {

    ExerciseLog addNewExerciseLog(ExerciseLog newExerciseLog);


    ExerciseLog getExerciseLogById(int logId);

    ExerciseLogDto getExerciseLogBySessionId(int sessionId);


    List<LogDto> getExerciseLogByUserId(int userId);



}
