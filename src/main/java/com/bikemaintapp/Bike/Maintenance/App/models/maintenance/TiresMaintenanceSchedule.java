package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import javax.persistence.Entity;


@Entity
public class TiresMaintenanceSchedule extends MaintenanceSchedule{

    public TiresMaintenanceSchedule(){

        //addInterval(100,"check pressure and inspect for any cracks");
        //addInterval(2500,"Replace tires if tread is fully worn down");
    }
}

