package com.techelevator.model;


import java.util.Date;


public class LogDto {


    private String date;


    private int duration;


    private int equipmentId;
    private String equipmentName;
    private int sessionId;
    private int userId;
    private int reps;
    private double weight;


    private int totalReps;






    public LogDto(String date, int duration, int equipmentId, String equipmentName, int sessionId, int userId, int reps, double weight,int total_reps) {
        this.date = date;
        this.duration = duration;
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.sessionId = sessionId;
        this.userId = userId;
        this.reps = reps;
        this.weight = weight;
        this.totalReps = totalReps;
    }


    public LogDto() {


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


    public int getEquipmentId() {
        return equipmentId;
    }


    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }


    public String getEquipmentName() {
        return equipmentName;
    }


    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
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


    public int getReps() {
        return reps;
    }


    public void setReps(int reps) {
        this.reps = reps;
    }


    public double getWeight() {
        return weight;
    }


    public void setWeight(double weight) {
        this.weight = weight;
    }


    public int getTotalReps() {
        return totalReps;
    }


    public void setTotalReps(int totalReps) {
        this.totalReps = totalReps;
    }
}
