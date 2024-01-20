package com.techelevator.model;

public class Equipment {
    private int equipmentId;
    private int totalReps;
    private String equipmentName;
    private String equipmentType;
    private String equipmentPhoto;
    private String instructionsVideo;
    private String instructionsAnimation;
    private String targetedArea;


    public Equipment() {
    }

    public Equipment(int equipmentId, int totalReps, String equipmentName, String equipmentType, String equipmentPhoto, String instructionsVideo, String instructionsAnimation, String targetedArea) {
        this.equipmentId = equipmentId;
        this.totalReps = totalReps;
        this.equipmentName = equipmentName;
        this.equipmentType = equipmentType;
        this.equipmentPhoto = equipmentPhoto;
        this.instructionsVideo = instructionsVideo;
        this.instructionsAnimation = instructionsAnimation;
        this.targetedArea = targetedArea;
    }


    public int getEquipmentId() {
        return equipmentId;
    }


    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }


    public int getTotalReps() {
        return totalReps;
    }


    public void setTotalReps(int totalReps) {
        this.totalReps = totalReps;
    }


    public String getEquipmentName() {
        return equipmentName;
    }


    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }


    public String getEquipmentType() {
        return equipmentType;
    }


    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }


    public String getEquipmentPhoto() {
        return equipmentPhoto;
    }


    public void setEquipmentPhoto(String equipmentPhoto) {
        this.equipmentPhoto = equipmentPhoto;
    }


    public String getInstructionsVideo() {
        return instructionsVideo;
    }


    public void setInstructionsVideo(String instructionsVideo) {
        this.instructionsVideo = instructionsVideo;
    }


    public String getInstructionsAnimation() {
        return instructionsAnimation;
    }


    public void setInstructionsAnimation(String instructionsAnimation) {
        this.instructionsAnimation = instructionsAnimation;
    }


    public String getTargetedArea() {
        return targetedArea;
    }


    public void setTargetedArea(String targetedArea) {
        this.targetedArea = targetedArea;
    }

}
