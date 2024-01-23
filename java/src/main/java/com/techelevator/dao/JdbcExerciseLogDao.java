package com.techelevator.dao;
import com.techelevator.model.ExerciseLog;
import com.techelevator.model.GymSession;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcExerciseLogDao implements ExerciseLogDao {
    private JdbcTemplate jdbcTemplate;


    public JdbcExerciseLogDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    @Override
    public ExerciseLog addNewExerciseLog(ExerciseLog newExerciseLog) {
        ExerciseLog addedExerciseLog = null;
        String sql = "INSERT INTO exercise_log (session_id,equipment_id,reps,weight)" +
                "VALUES(?,?,?,?) RETURNING log_id;";


        int newExerciseLogId = jdbcTemplate.queryForObject(sql,int.class,newExerciseLog.getSessionId(),newExerciseLog.getEquipmentId(),newExerciseLog.getReps(),newExerciseLog.getWeight());
        addedExerciseLog = getExerciseLogById(newExerciseLogId);


        return addedExerciseLog;
    }


    @Override
    public ExerciseLog getExerciseLogById(int logId) {
        ExerciseLog exerciseLog = null;


        String sql = "SELECT * FROM exercise_log" +
                " WHERE log_id = ?;";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql,logId);
        while (row.next()){
            exerciseLog = mapRowsetToExerciseLog(row);
        }
        return exerciseLog;
    }


    public ExerciseLog mapRowsetToExerciseLog(SqlRowSet row) {
        ExerciseLog exerciseLog = new ExerciseLog();
        exerciseLog.setLogId(row.getInt("log_id"));
        exerciseLog.setSessionId(row.getInt("session_id"));
        exerciseLog.setEquipmentId(row.getInt("equipment_id"));
        exerciseLog.setReps(row.getInt("reps"));
        exerciseLog.setWeight(row.getDouble("weight"));


        return exerciseLog;
    }
}

