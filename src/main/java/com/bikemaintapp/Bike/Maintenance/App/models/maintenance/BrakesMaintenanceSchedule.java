package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import com.bikemaintapp.Bike.Maintenance.App.models.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BrakesMaintenanceSchedule {

    @Id
    @GeneratedValue // Auto generates primary key
    private int id;

    @OneToOne
    private Component component;

    private int milesSinceMaint100 = 0;
    private int milesSinceMaint500 = 0;
    private int milesSinceMaint2500 = 0;
    private int undoMiles;

    public void addMiles(int miles) {
        milesSinceMaint100 += miles;
        milesSinceMaint500 += miles;
        milesSinceMaint2500 += miles;
        undoMiles = miles;

        if (milesSinceMaint100 >= 100) {
            notifyMaint(100);
        }

        if (milesSinceMaint500 >= 500) {
            notifyMaint(500);
        }
        if (milesSinceMaint2500 >= 2500) {
            notifyMaint(2500);
        }

    }

    public BrakesMaintenanceSchedule() {}

    public String notifyMaint(int miles) {

        if (miles >= 500 && miles < 2500) {
            return "Check brake cables, increase tension if needed";
        } else if (miles >= 2500 && miles < 5000){
            return "Lubricate cables, increase tension if needed";
        } else {
            return "Check brake pads and replace if worn out";
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

    public int getMilesSinceMaint2500() {
        return milesSinceMaint2500;
    }

    public void setMilesSinceMaint2500(int milesSinceMaint2500) {
        this.milesSinceMaint2500 = milesSinceMaint2500;
    }

    public int getUndoMiles() {
        return undoMiles;
    }

    public void setUndoMiles(int undoMiles) {
        this.undoMiles = undoMiles;
    }
}
