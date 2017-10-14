package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import javax.persistence.Entity;


@Entity
public class TiresMaintenanceSchedule extends MaintenanceSchedule{

    public TiresMaintenanceSchedule(){

        //Messages
        setInstructions(
                "check pressure and inspect for any cracks",
                "Replace tires if tread is fully worn down");
        //Set these with the mileage between intervals
        setInterval(100,2500);
    }
}

