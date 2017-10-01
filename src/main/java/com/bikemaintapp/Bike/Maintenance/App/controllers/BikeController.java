package com.bikemaintapp.Bike.Maintenance.App.controllers;


import com.bikemaintapp.Bike.Maintenance.App.models.Bike;
import com.bikemaintapp.Bike.Maintenance.App.models.data.BikeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

// Bike controller for creating a viewing bikes
@Controller
@RequestMapping("bike")
public class BikeController {

    // Link the bike DB
    // TODO link rides and users to the bike
    @Autowired
    BikeDao bikeDao;

    // display all the Existing bike
    @RequestMapping(value="")
    public String index(Model model){
        // TODO get all the bikes form the database and display though this route
        model.addAttribute("title","View Bikes");
        return "bike/index";
    }

    // Display the bike add page and creates a bike object
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddBikeForm(Model model){
        model.addAttribute("title", "Add Bike");
        model.addAttribute(new Bike());
        return "bike/add";
    }

    // This view process the form from the bike form
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddBikeForm(@ModelAttribute @Valid Bike newBike, Errors errors, Model model){


        // If the value is not met then return user to the add page
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Bike");
            model.addAttribute("bike", newBike);
            return "bike/add";
        }
        // If the values are met the process form
        bikeDao.save(newBike);
        model.addAttribute("bike",newBike);
        model.addAttribute("title","Add Bike");
        return "bike/add";
    }

}
