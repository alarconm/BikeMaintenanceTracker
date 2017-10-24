package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//@Entity.
@Embeddable
public class MaintInterval {

   /* @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name ="maintenanceSchedule_id")
    private MaintenanceSchedule maintenanceSchedule;*/

    private int intervalMiles;
    private int milesRemaining;
    private String instructions;

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
        this.instructions = instructions;
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
    /*public MaintenanceSchedule getMaintenanceSchedule(){
        return maintenanceSchedule;
    }
    public void setMaintenanceSchedule(MaintenanceSchedule maintenanceSchedule){
        this.maintenanceSchedule = maintenanceSchedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/
}
