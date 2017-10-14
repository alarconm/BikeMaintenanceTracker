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

    private int milesSinceMaint500 = 0;
    private int milsSinceMaint1000 = 0;
    private int undoMiles; //holds the last entered ride so that an undo button can be used.

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

    public String notifyMaint(int miles) {

        if (miles >= 500 && miles < 1000) {
            return "Clean and lubricate suspension";
        } else {
            return "Full suspension teardown and rebuild";
        }
    }
}
