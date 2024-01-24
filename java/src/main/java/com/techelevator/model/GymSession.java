package com.techelevator.model;

import java.util.Date;
import java.time.LocalDate;


public class GymSession {


    private int sessionId;
    private int userId;
    private String date;
    private int duration;


    public GymSession(int sessionId, int userId, String date, int duration) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.date = date;
        this.duration = duration;
    }


    public GymSession() {


    }


    public int getSessionId() {
        return sessionId;
    }


    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }


    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public int getDuration() {
        return duration;
    }


    public void setDuration(int duration) {
        this.duration = duration;
    }
}
