package com.bikemaintapp.Bike.Maintenance.App.models.maintenance;

import javax.persistence.Entity;


@Entity
public class DriveTrainMaintenanceSchedule extends MaintenanceSchedule{

    public DriveTrainMaintenanceSchedule(){

        //Messages
        setInstructions(
                "Lube chain, cassette and pedals",
                "Perform full cleaning of chain and cassette. Re-lube all parts",
                "Replace chain and cassette");
        //Set these three bits with the mileage between intervals
        setInterval(100,500,500);
    }

}
