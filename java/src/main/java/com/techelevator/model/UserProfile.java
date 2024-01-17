package com.techelevator.model;

public class UserProfile {

    private int profileId;
    private int userId;
    private String name;
    private String email;
    private String photo_url;
    private int age;
    private double height;
    private double weight;
    private String experienceLevel;

    public UserProfile() {}

    public UserProfile(int profileId, int userId, String name, String email, String photo_url, int age, double height, double weight, String experienceLevel) {
        this.profileId = profileId;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.photo_url = photo_url;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.experienceLevel = experienceLevel;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }
}
