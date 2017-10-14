package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import com.bikemaintapp.Bike.Maintenance.App.models.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DriveTrainMaintenanceSchedule {

    @Id
    @GeneratedValue // Auto generates primary key
    private int id;

    @OneToOne
    private Component component;

    // each variable is tied to maintenance performed at the specific mileage interval
    private int milesSinceMaint100 = 0;
    private int milesSinceMaint500 = 0;
    private int milesSinceMaint5000 = 0;
    private int undoMiles; // this holds the last entered mileage so that an undo/remove last ride button can be used

    // whenever a ride is recorded the mileage is sent to the component, that will then call this method
    public void addMiles(int miles) {
        milesSinceMaint100 += miles;
        milesSinceMaint500 += miles;
        milesSinceMaint5000 += miles;
        undoMiles = miles;

        if (milesSinceMaint100 >= 100) {
            notifyMaint(100);
        }

        if (milesSinceMaint500 >= 500) {
            notifyMaint(500);
        }
        if (milesSinceMaint5000 >= 5000) {
            notifyMaint(5000);
        }
    }

    public DriveTrainMaintenanceSchedule() {
    }

    // if the mileage on a component hits the maximum for a maintenance interval it will call this method
    // then it will return the string explaining the maintenance that needs to be performed

    public String notifyMaint(int miles) {

        if (miles >= 100 && miles < 500) {
            return "Lube chain, cassette and pedals";
        } else if (miles >= 500 && miles < 5000) {
            return "Perform full cleaning of chain and cassette. Re-lube all parts";
        } else {
            return "Replace chain and cassette";
        }
    }

    public int getId() {
        return id;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public int getMilesSinceMaint100() {
        return milesSinceMaint100;
    }

    public void setMilesSinceMaint100(int milesSinceMaint100) {
        this.milesSinceMaint100 = milesSinceMaint100;
    }

    public int getMilesSinceMaint500() {
        return milesSinceMaint500;
    }

    public void setMilesSinceMaint500(int milesSinceMaint500) {
        this.milesSinceMaint500 = milesSinceMaint500;
    }

    public int getMilesSinceMaint5000() {
        return milesSinceMaint5000;
    }

    public void setMilesSinceMaint5000(int milesSinceMaint5000) {
        this.milesSinceMaint5000 = milesSinceMaint5000;
    }

    public int getUndoMiles() {
        return undoMiles;
    }

    public void setUndoMiles(int undoMiles) {
        this.undoMiles = undoMiles;
    }
}
