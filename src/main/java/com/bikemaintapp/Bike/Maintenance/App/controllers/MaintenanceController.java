package com.bikemaintapp.Bike.Maintenance.App.controllers;
import com.bikemaintapp.Bike.Maintenance.App.models.data.MaintenanceDao;
import com.bikemaintapp.Bike.Maintenance.App.models.maintenance.MaintenanceSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MaintenanceController {

    @Autowired
    private MaintenanceDao maintDao;

    //TODO Authentication checks, right now anyone can go here and itll reset the matching maintenance scheudle
    @RequestMapping("/maintreset/{id}")
    public String greeting(@PathVariable("id") int id) {
        MaintenanceSchedule maintShed = maintDao.findOne(id);
        maintShed.resetInterval(0);
        maintDao.save(maintShed);
        return "hotdog";
    }
}