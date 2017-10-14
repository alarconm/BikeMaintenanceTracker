package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import javax.persistence.Entity;


@Entity
public class BrakesMaintenanceSchedule extends MaintenanceSchedule{

    public BrakesMaintenanceSchedule(){
        addInterval(100,"Check brake cables, increase tension if needed");
        addInterval(500,"Lubricate cables, increase tension if needed");
        addInterval(2500,"Check brake pads and replace if worn out");
    }

}
