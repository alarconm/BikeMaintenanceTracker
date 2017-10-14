package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import com.bikemaintapp.Bike.Maintenance.App.models.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TiresMaintenanceSchedule {

    @Id
    @GeneratedValue // Auto generates primary key
    private int id;

    @OneToOne
    private Component component;

    private int milesSinceMaint100 = 0;
    private int milesSinceMaint2500 = 0;
    private int undoMiles; //holds the last entered ride so that an undo button can be used.

    public void addMiles(int miles) {
        milesSinceMaint100 += miles;
        milesSinceMaint2500 += miles;
        undoMiles = miles;

        if (milesSinceMaint100 >= 100) {
            notifyMaint(100);
        }
        if (milesSinceMaint2500 >= 2500) {
            notifyMaint(2500);
        }

    }

    public TiresMaintenanceSchedule() {}

    public String notifyMaint(int miles) {

        if (miles >= 100 && miles < 2500) {
            return "check pressure and inspect for any cracks";
        } else {
            return "Replace tires if tread is fully worn down";
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
