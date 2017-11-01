package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import javax.persistence.*;

@Embeddable
public class MaintInterval {

    private int intervalMiles;
    private int milesRemaining;
    private String instructions;
    private String video;

    private int prevMilesRemaining; //for undo; ONLY for resetting an accidental "perform maintenance"

    public void addMiles(int miles){
        milesRemaining = milesRemaining - miles;
    }
    public boolean isDue(){
        return(milesRemaining < 0);
    }
    public void resetMilesRemaining(){
        prevMilesRemaining = milesRemaining;
        milesRemaining = intervalMiles;
    }

    //Only for resetting an accidental "perform maintenance", Not for undoing 'addMiles'
    public void undoMileageReset(){
        milesRemaining = prevMilesRemaining;
    }

    //Main Constructor for MaintenanceSchedule to call
    public MaintInterval(int intervalMiles, String instructions){
        this.intervalMiles = intervalMiles;
        this.milesRemaining = intervalMiles;
        this.instructions = instructions;
    }

    //Constructor with video on maintenance
    public MaintInterval(int intervalMiles, String instructions, String video) {
        this.intervalMiles = intervalMiles;
        this.milesRemaining = intervalMiles;
        this.instructions = instructions;
        this.video = video;
    }

    //Spring methods
    public MaintInterval(){

    }
    public int getIntervalMiles(){
        return intervalMiles;
    }
    public void setIntervalMiles(int intervalMiles){
        this.intervalMiles = intervalMiles;
    }
    public int getMilesRemaining(){
        return milesRemaining;
    }
    public void setMilesRemaining(int milesRemaining){
        this.milesRemaining = milesRemaining;
    }
    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions){
        this.instructions = instructions;
    }
    public int getPrevMilesRemaining() {
        return prevMilesRemaining;
    }
    public void setPrevMilesRemaining(int prevMilesRemaining) {
        this.prevMilesRemaining = prevMilesRemaining;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
