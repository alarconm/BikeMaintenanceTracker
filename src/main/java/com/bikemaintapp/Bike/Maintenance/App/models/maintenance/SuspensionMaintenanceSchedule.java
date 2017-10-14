package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import javax.persistence.Entity;


@Entity
public class SuspensionMaintenanceSchedule extends MaintenanceSchedule{

    public SuspensionMaintenanceSchedule(){

        //Messages
        setInstructions(
                "Clean and lubricate suspension",
                "Full suspension teardown and rebuild");
        //Set these with the mileage between intervals
        setInterval(100,500);
    }
}

