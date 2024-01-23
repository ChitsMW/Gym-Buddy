package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.GymSession;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcGymSessionDao implements GymSessionDao {


    private JdbcTemplate jdbcTemplate;


    public JdbcGymSessionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    @Override
    public GymSession getGymSessionBySessionId(int sessionId) {
        GymSession gymSession = null;




        String sql = "SELECT session_id, user_id, date, duration" +
                " FROM gym_session" +
                " WHERE session_id = ?;";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, sessionId);
        while (row.next()) {
            gymSession = mapRowsetToGymSession(row);
        }
        return gymSession;
    }


    @Override
    public List <GymSession> getGymSessionByUserId(int userId) {
        List<GymSession> userGymSessions = new ArrayList<>();




        String sql = "SELECT session_id, user_id, date, duration" +
                " FROM gym_session" +
                " WHERE user_id = ?;";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, userId);
        while (row.next()) {
            GymSession gymSession = mapRowsetToGymSession(row);
            userGymSessions.add(gymSession);
        }


        return userGymSessions;


    }


    @Override
    public List<GymSession> getAllGymSessions() {


        List<GymSession> allGymSession = new ArrayList<>();
        String sql = "SELECT * FROM gym_session";




        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            GymSession gymSession = mapRowsetToGymSession(results);
            allGymSession.add(gymSession);
        }




        return allGymSession;
    }


    @Override
    public GymSession addNewGymSession(GymSession newGymSession) {




        GymSession addedGymSession = null;
        String sql = "INSERT INTO gym_session (user_id,date) " +
                "VALUES(?, ?) RETURNING session_id;";




        int newGymSessionId = jdbcTemplate.queryForObject(sql, int.class, newGymSession.getUserId(), newGymSession.getDate()
        );
        addedGymSession = getGymSessionBySessionId(newGymSessionId);




        return addedGymSession;
    }






    public GymSession mapRowsetToGymSession(SqlRowSet row) {
        GymSession gymSession = new GymSession();
        gymSession.setSessionId(row.getInt("session_id"));
        gymSession.setUserId(row.getInt("user_id"));
        gymSession.setDate(row.getDate("date"));
        gymSession.setDuration(row.getInt("duration"));


        return gymSession;
    }
}
