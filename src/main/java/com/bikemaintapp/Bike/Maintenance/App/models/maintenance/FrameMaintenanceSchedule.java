package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import javax.persistence.Entity;


@Entity
public class FrameMaintenanceSchedule extends MaintenanceSchedule{

    public FrameMaintenanceSchedule(){

        //Messages
        setInstructions(
                "Clean thoroughly and inspect for any cracks",
                "Tear down frame, clean and inspect for cracks, lube/grease all parts");
        //Set these three with the mileage between intervals
        setInterval(100,500);
    }

}