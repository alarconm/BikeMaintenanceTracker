package com.bikemaintapp.Bike.Maintenance.App.controllers;

import com.bikemaintapp.Bike.Maintenance.App.models.Ride;
import com.bikemaintapp.Bike.Maintenance.App.models.data.BikeDao;
import com.bikemaintapp.Bike.Maintenance.App.models.data.RideDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("ride")
public class RideController {

    @Autowired
    RideDao rideDao;
    @Autowired
    BikeDao bikeDao;

    // Display list of rides sorted by date
    @RequestMapping(value = "")
    public String index(Model model){
        // TODO load ride data from db and display
        return "ride/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddRideForm(Model model){
        model.addAttribute("bikes",bikeDao.findAll());
        model.addAttribute(new Ride());
        return "ride/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddRideForm(@ModelAttribute @Valid Ride newRide, Errors errors, Model model){

        if(errors.hasErrors()){
            return "ride/add";
        }
        model.addAttribute("ride",newRide);
        rideDao.save(newRide);
        return "ride/index";
    }
}
