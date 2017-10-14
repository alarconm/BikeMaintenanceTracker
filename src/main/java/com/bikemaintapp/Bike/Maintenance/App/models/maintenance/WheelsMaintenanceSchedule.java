package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import com.bikemaintapp.Bike.Maintenance.App.models.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WheelsMaintenanceSchedule {

    @Id
    @GeneratedValue // Auto generates primary key
    private int id;

    @OneToOne
    private Component component;

    private int milesSinceMaint500 = 0;
    private int undoMiles; //holds the last entered ride so that an undo button can be used.

    public void addMiles(int miles) {
        milesSinceMaint500 += miles;
        undoMiles = miles;

        if (milesSinceMaint500 >= 500) {
            notifyMaint(500);
        }
    }

    public WheelsMaintenanceSchedule() {}

    public String notifyMaint(int miles) {
        return "Check spoke tension. Clean and inspect wheels for cracks. If cracked replace wheels";
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

    public int getMilesSinceMaint500() {
        return milesSinceMaint500;
    }

    public void setMilesSinceMaint500(int milesSinceMaint500) {
        this.milesSinceMaint500 = milesSinceMaint500;
    }

    public int getUndoMiles() {
        return undoMiles;
    }

    public void setUndoMiles(int undoMiles) {
        this.undoMiles = undoMiles;
    }
}
