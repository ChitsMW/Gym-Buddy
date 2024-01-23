package com.techelevator.dao;
import com.techelevator.model.Equipment;
import com.techelevator.model.ExerciseLog;

public interface ExerciseLogDao {

    ExerciseLog addNewExerciseLog(ExerciseLog newExerciseLog);


    ExerciseLog getExerciseLogById(int logId);

}
