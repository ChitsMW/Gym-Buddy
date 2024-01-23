package com.techelevator.model;

public class ExerciseLog {

    private int logId;
    private int sessionId;
    private int equipmentId;
    private int reps;
    private double weight;


    public ExerciseLog() {
    }


    public ExerciseLog(int logId, int sessionId, int equipmentId, int reps, double weight) {
        this.logId = logId;
        this.sessionId = sessionId;
        this.equipmentId = equipmentId;
        this.reps = reps;
        this.weight = weight;
    }


    public int getLogId() {
        return logId;
    }


    public void setLogId(int logId) {
        this.logId = logId;
    }


    public int getSessionId() {
        return sessionId;
    }


    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }


    public int getEquipmentId() {
        return equipmentId;
    }


    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
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


}
