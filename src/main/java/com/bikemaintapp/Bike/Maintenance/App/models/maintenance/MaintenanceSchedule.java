package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import com.bikemaintapp.Bike.Maintenance.App.models.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public abstract class MaintenanceSchedule {

    @Id
    @GeneratedValue // Auto generates primary key
    private int id;

    @OneToOne
    private Component component;

    //This these in subclasses
    private String maintInstructionsOne = "Default message for instructionsOne";
    private String maintInstructionsTwo = "Default message for instructionsTwo";
    private String maintInstructionsThree = "Default message for instructionsThree";
    private int[] interval = null;

    //each variable is tied to maintenance performed at the specific mileage interval
    /*This array is replacing milesSinceIntervalOne,milesSinceIntervalTwo and milesSinceIntervalThree.
      milesSinceIntervalOne is now the first element in the array, and so on*/
    private int[] milesSinceMaintInterval = {0,0,0};

    // this holds the last entered mileage so that an undo/remove last ride button can be used
    private int undoMiles;

    // whenever a ride is recorded the mileage is sent to the component, that will then call this method
    public void addMiles(int miles) {

        undoMiles = miles;

        for(int i=0;i < 3;i++){
            milesSinceMaintInterval[i] += miles;  //replacing milesSinceMaintIntervalOne += miles;
            if(milesSinceMaintInterval[i] >= interval[i]){
                notifyMaint(milesSinceMaintInterval[i]); //TODO do somethign with what notifyMaint() returns
            }
        }
    }

    public MaintenanceSchedule() {

    }

    // if the mileage on a component hits the maximum for a maintenance interval it will call this method
    // then it will return the string explaining the maintenance that needs to be performed.
    //TODO Fix this- repeating mileage check of addMiles() and returned values not going anywhere
    //Todo figure out if this should be overridden per subclass for different combinations of messages
    public String notifyMaint(int miles) {

        if (miles >= interval[0] && miles < interval[1]) {
            return maintInstructionsOne;
        } else if (miles >= interval[1] && miles < interval[2]) {
            return maintInstructionsTwo;
        } else {
            return maintInstructionsThree;
        }
    }

    //This is for our subclasses to set their maintenance instructions
    protected void setInstructions(String one,String two,String three){
        maintInstructionsOne = one;
        maintInstructionsTwo = two;
        maintInstructionsThree = three;
    }
    //This is for our subclasses to set their mileage intervals
    protected void setInterval(int one, int two, int three){
        interval = new int[] {one,two,three};
    }

    //Setters and Getters
    public int getId() {
        return id;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
    public int getMilesSinceMaintInterval(int i) {
        return milesSinceMaintInterval[i];
    }

    public void setMilesSinceMaintInterval(int i,int milesSinceMaint) {
        this.milesSinceMaintInterval[i] = milesSinceMaint;
    }
    public int getUndoMiles() {
        return undoMiles;
    }

    public void setUndoMiles(int undoMiles) {
        this.undoMiles = undoMiles;
    }
}
