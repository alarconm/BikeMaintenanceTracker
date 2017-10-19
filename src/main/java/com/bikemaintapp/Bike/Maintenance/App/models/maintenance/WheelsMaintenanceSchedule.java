package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import javax.persistence.Entity;


@Entity
public class WheelsMaintenanceSchedule extends MaintenanceSchedule{

    public WheelsMaintenanceSchedule(){
        addInterval(500,"Check spoke tension. Clean and inspect wheels for cracks. If cracked replace wheels.");
    }
}
