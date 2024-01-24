package com.techelevator.dao;
import com.techelevator.model.ExerciseLog;
import com.techelevator.model.ExerciseLogDto;
import com.techelevator.model.GymSession;
import com.techelevator.model.LogDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ExerciseLogDto getExerciseLogBySessionId(int sessionId) {
        ExerciseLogDto newExerciseLog = null;
        String sql = "SELECT exercise_log.session_id, gym_session.user_id, exercise_log.reps, exercise_log.weight\n" +
                "                FROM exercise_log\n" +
                "                JOIN gym_session ON exercise_log.session_id = gym_session.session_id\n" +
                "                WHERE exercise_log.session_id = ?";


        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, sessionId);
        while (row.next()) {
            newExerciseLog = newMapRowsetToLog(row);


        }
        return newExerciseLog;
    }


    @Override
    public List<LogDto> getExerciseLogByUserId(int userId) {
        List<LogDto> logList = new ArrayList<>();


        String sql = "SELECT gym_session.date, gym_session.duration,equipment.equipment_id, equipment.equipment_name, gym_session.session_id, gym_session.user_id, exercise_log.reps, exercise_log.weight,equipment.total_reps" +
                " FROM gym_session" +
                " JOIN exercise_log ON gym_session.session_id = exercise_log.session_id" +
                " JOIN equipment ON equipment.equipment_id = exercise_log.equipment_id" +
                " WHERE gym_session.user_id = ?";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, userId);
        while (row.next()) {
            LogDto logDto = MapRowsetToLog(row);
            logList.add(logDto);
        }
        return logList;
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

    public ExerciseLogDto newMapRowsetToLog(SqlRowSet row) {
        ExerciseLogDto newExerciseLog = new ExerciseLogDto();
        newExerciseLog.setSessionId(row.getInt("session_id"));
        newExerciseLog.setUserId(row.getInt("user_id"));
        newExerciseLog.setReps(row.getInt("reps"));
        newExerciseLog.setWeight(row.getDouble("weight"));


        return newExerciseLog;




    }


    public LogDto MapRowsetToLog(SqlRowSet row) {
        LogDto newLog = new LogDto();
        newLog.setDate(row.getString("date"));
        newLog.setDuration(row.getInt("Duration"));
        newLog.setEquipmentId(row.getInt("equipment_id"));
        newLog.setEquipmentName(row.getString("equipment_name"));
        newLog.setSessionId(row.getInt("session_id"));
        newLog.setUserId(row.getInt("user_id"));
        newLog.setReps(row.getInt("reps"));
        newLog.setWeight(row.getDouble("weight"));
        newLog.setTotalReps(row.getInt("total_reps"));




        return newLog;
    }
}

