package com.techelevator.dao;

import com.techelevator.model.GymSession;


import java.util.List;


public interface GymSessionDao {


    GymSession getGymSessionBySessionId(int sessionId);

    List<GymSession> getGymSessionByUserId(int userId);

    List<GymSession> getAllGymSessions();

    GymSession addNewGymSession(GymSession newGymSession);

    GymSession addDuration(int gymSessionId, Integer duration);
}
