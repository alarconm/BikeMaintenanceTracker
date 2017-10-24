package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import com.bikemaintapp.Bike.Maintenance.App.models.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class MaintenanceSchedule {

    @Id
    @GeneratedValue // Auto generates primary key
    private int id;

    @OneToOne(cascade = {CascadeType.ALL})
    private Component component;

   // @OneToMany(mappedBy = "maintenanceSchedule")

    //@ElementCollection(targetClass = MaintInterval.class)

    //@OneToMany(mappedBy = "maintenanceSchedule")
    @Embedded
    private List<MaintInterval> intervals;

    //New mileage is sent from ride-> component-> this.addMiles() -> MaintInterval.addMiles()
    public void addMiles(int miles) {
        for (MaintInterval interval:intervals){
            interval.addMiles(miles);
            System.out.println("hotdog");
            System.out.println(miles);
        }
    }

    //You should be able to iterate through this in thymleaf and display each ones mileage and instructions
    public List<MaintInterval> getDueIntervals(){
        List<MaintInterval> dueIntervals = new ArrayList<>();
        for (MaintInterval interval:intervals){
            if(interval.isDue()){
                dueIntervals.add(interval);
            }
        }
        return dueIntervals;
    }

    //resets an interval by its index in the this MaintenanceSchedule's list
    public void resetInterval(int index){
        intervals.get(index).resetMilesRemaining();
    }

    public void addInterval(int miles, String instructions){
        MaintInterval temp = new MaintInterval();

        //temp.setMaintenanceSchedule(this);
       intervals.add(temp);
        //intervals.add(new MaintInterval(miles,instructions));
       // for (MaintInterval interval:intervals){
         //   interval.setMaintenanceSchedule(this);
       // }
    }

    //Spring methods
    public MaintenanceSchedule() {
    }
    //Setters and Getters
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public List<MaintInterval> getIntervals() {
        return intervals;
    }
    public void setIntervals(List<MaintInterval> intervals){
        this.intervals = intervals;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

}
