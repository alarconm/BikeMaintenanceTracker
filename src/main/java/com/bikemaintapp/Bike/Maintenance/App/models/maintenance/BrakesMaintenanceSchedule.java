package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import javax.persistence.Entity;


@Entity
public class BrakesMaintenanceSchedule extends MaintenanceSchedule{

    public BrakesMaintenanceSchedule(){

        //Messages
        setInstructions(
                "Check brake cables, increase tension if needed",
                "Lubricate cables, increase tension if needed",
                "Lubricate cables, increase tension if needed");
        //Set these three bits with the mileage between intervals
        setInterval(100,500,2500);
    }

}
