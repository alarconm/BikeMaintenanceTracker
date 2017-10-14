package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import javax.persistence.Entity;


@Entity
public class FrameMaintenanceSchedule extends MaintenanceSchedule{

    public FrameMaintenanceSchedule(){

        //Messages
        addInterval(100,"Clean thoroughly and inspect for any cracks");
        addInterval(500,"Tear down frame, clean and inspect for cracks, lube/grease all parts");
    }
}