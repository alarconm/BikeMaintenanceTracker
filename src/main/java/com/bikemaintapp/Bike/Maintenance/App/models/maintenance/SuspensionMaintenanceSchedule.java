package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import com.bikemaintapp.Bike.Maintenance.App.models.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SuspensionMaintenanceSchedule {

    @Id
    @GeneratedValue // Auto generates primary key
    private int id;

    @OneToOne
    private Component component;

    // each variable is tied to maintenance performed at the specific mileage interval
    private int milesSinceMaint500 = 0;
    private int milsSinceMaint1000 = 0;
    private int undoMiles; //holds the last entered ride so that an undo button can be used.

    // whenever a ride is recorded the mileage is sent to the component, that will then call this method
    public void addMiles(int miles) {
        milesSinceMaint500 += miles;
        milsSinceMaint1000 += miles;
        undoMiles = miles;

        if (milesSinceMaint500 >= 500) {
            notifyMaint(500);
        }
        if (milsSinceMaint1000 >= 1000) {
            notifyMaint(1000);
        }
    }

    public SuspensionMaintenanceSchedule() {}

    // if the mileage on a component hits the maximum for a maintenance interval it will call this method
    // then it will return the string explaining the maintenance that needs to be performed
    public String notifyMaint(int miles) {

        if (miles >= 500 && miles < 1000) {
            return "Clean and lubricate suspension";
        } else {
            return "Full suspension teardown and rebuild";
        }
    }
}
