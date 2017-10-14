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
    private int[] interval = null;
    private String[] maintInstructions = null;

    //each variable is tied to maintenance performed at the specific mileage interval
    /*This array is replacing milesSinceIntervalOne,milesSinceIntervalTwo and milesSinceIntervalThree.
      milesSinceIntervalOne is now the first element in the array, and so on*/
    private int[] milesSinceMaintInterval = {0,0,0};

    // this holds the last entered mileage so that an undo/remove last ride button can be used
    private int undoMiles;

    // whenever a ride is recorded the mileage is sent to the component, that will then call this method
    public void addMiles(int miles) {

        undoMiles = miles;

        //This runs once for each element in interval[]
        for(int i=0;i < interval.length;i++){
            milesSinceMaintInterval[i] += miles;  //replacing milesSinceMaintIntervalOne += miles;
            if(milesSinceMaintInterval[i] >= interval[i]){
                //notifyMaint(milesSinceMaintInterval[i]); //TODO do somethign with what notifyMaint() returns

                //return maintInstructions[i]; //We can just return the instruction matching the index of this interval
            }
        }
    }

    public MaintenanceSchedule() {

    }

    // if the mileage on a component hits the maximum for a maintenance interval it will call this method
    // then it will return the string explaining the maintenance that needs to be performed.
    //TODO returned values not going anywhere
    //Todo figure out if this should be overridden per subclass for different combinations of messages
    public String notifyMaint(int miles) {

        if (miles < interval[1]) {
            return maintInstructions[0];
        } else if (miles < interval[2]) {
            return maintInstructions[1];
        } else {
            return maintInstructions[2];
        }
    }

    //This is for our subclasses to set their maintenance instructions
    protected void setInstructions(String one,String two,String three){
        maintInstructions = new String[] {one,two,three};
    }
    //This is for our subclasses to set their mileage intervals
    protected void setInterval(int one, int two, int three){
        interval = new int[] {one,two,three};
    }
    //TODO get rid of these overloaded functions. Should make a new class holding interval
    //TODO numbers and instructions as one object, and then keep a list of those. then could have a simple add() in the subclasses and no overloading
    //We could also make a more dynamic setter that accepts arrays, but then the subclasses arent as readable because you lose some IDE notes
    protected void setInstructions(String one,String two){
        maintInstructions = new String[] {one,two};
    }
    protected void setInterval(int one, int two){
        interval = new int[] {one,two};
    }
    protected void setInstructions(String one){
        maintInstructions = new String[] {one};
    }
    protected void setInterval(int one){
        interval = new int[] {one};
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
