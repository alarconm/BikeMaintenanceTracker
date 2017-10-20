package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import com.bikemaintapp.Bike.Maintenance.App.models.Component;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class MaintenanceSchedule {

    @Id
    @GeneratedValue // Auto generates primary key
    private int id;

    @OneToOne(cascade = {CascadeType.ALL})
    private Component component;

    //These lists could should maybe all be together in a subclass...but its fine
    @ElementCollection(targetClass=Integer.class)
    private List<Integer> intervals = new ArrayList<Integer>();
    @ElementCollection(targetClass=String.class)
    private List<String> maintInstructions = new ArrayList<String>();
    @ElementCollection(targetClass=Integer.class)
    private List<Integer> milesSinceMaintInterval = new ArrayList<Integer>();

    // this holds the last entered mileage so that an undo/remove last ride button can be used
    private int undoMiles;

    // whenever a ride is recorded the mileage is sent to the component, that will then call this method
    public void addMiles(int miles) {

        undoMiles = miles;
        //This runs once for each element in intervals[]
        for(int i=0;i < intervals.size();i++){
            int temp = milesSinceMaintInterval.get(i) + miles;
            milesSinceMaintInterval.set(i,temp);

            if(milesSinceMaintInterval.get(i) >= intervals.get(i)){
                //We can just return the instruction matching the index of this interval without further comparisons
                //return maintInstructions[i];

                //But we may need further processing or some other actions so lets keep this call for now
                notifyMaint(i);
            }
        }
    }

    //TODO look through all the parts intervals and return the lowest value
    //TODO or maybe something to return mileage details about ALL intervals
    public List<Integer> getMilesSinceMaintInterval(){
        return  milesSinceMaintInterval;
    }

    //This function can also be used to get the mileage instead of the prior convential springboot way
    //in case we need more flexibilty or something
    public int getMilesSinceLastMaint(){
        return milesSinceMaintInterval.get(0);
    }

    public MaintenanceSchedule() {

    }

    // if the mileage on a component hits the maximum for a maintenance interval it will call this method
    // then it will return the string explaining the maintenance that needs to be performed.
    //TODO returned values not going anywhere
    //Todo figure out if this should be overridden per subclass for different combinations of messages
    public void notifyMaint(int miles) {

        /*if (miles < interval[1]) {
            return maintInstructions[0];
        } else if (miles < interval[2]) {
            return maintInstructions[1];
        } else {
            return maintInstructions[2];
        }*/
    }

    protected void addInterval(int miles, String instructions){
        intervals.add(miles);
        maintInstructions.add(instructions);
        milesSinceMaintInterval.add(0);
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
        return milesSinceMaintInterval.get(i);
    }

    public void setMilesSinceMaintInterval(int i,int milesSinceMaint) {
        int temp = milesSinceMaintInterval.get(i);
        milesSinceMaintInterval.set(i,milesSinceMaint);
    }
    public int getUndoMiles() {
        return undoMiles;
    }

    public void setUndoMiles(int undoMiles) {
        this.undoMiles = undoMiles;
    }

    public List<Integer> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<Integer> intervals) {
        this.intervals = intervals;
    }

    public List<String> getMaintInstructions() {
        return maintInstructions;
    }

    public void setMaintInstructions(List<String> maintInstructions) {
        this.maintInstructions = maintInstructions;
    }

    public void setMilesSinceMaintInterval(List<Integer> milesSinceMaintInterval) {
        this.milesSinceMaintInterval = milesSinceMaintInterval;
    }

    /* old overload methods i was using
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
    */
}
