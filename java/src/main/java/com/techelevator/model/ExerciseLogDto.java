package com.techelevator.model;


public class ExerciseLogDto {




    private int sessionId;
    private int userId;
    private int reps;
    private double weight;


    public ExerciseLogDto(int sessionId, int userId, int reps, double weight) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.reps = reps;
        this.weight = weight;
    }


    public ExerciseLogDto() {


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
}

