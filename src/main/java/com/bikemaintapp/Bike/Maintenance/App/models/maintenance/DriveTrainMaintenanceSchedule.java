package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import javax.persistence.Entity;


@Entity
public class DriveTrainMaintenanceSchedule extends MaintenanceSchedule{

    public DriveTrainMaintenanceSchedule(){

        addInterval(100,"Lube chain, cassette and pedals");
        addInterval(500,"Perform full cleaning of chain and cassette. Re-lube all parts");
        addInterval(5000,"Replace chain and cassette");
    }
}
