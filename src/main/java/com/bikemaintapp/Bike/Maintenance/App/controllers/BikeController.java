package com.bikemaintapp.Bike.Maintenance.App.controllers;


import com.bikemaintapp.Bike.Maintenance.App.models.Bike;
import com.bikemaintapp.Bike.Maintenance.App.models.Ride;
import com.bikemaintapp.Bike.Maintenance.App.models.Component;
import com.bikemaintapp.Bike.Maintenance.App.models.ComponentType;
import com.bikemaintapp.Bike.Maintenance.App.models.User;
import com.bikemaintapp.Bike.Maintenance.App.models.data.BikeDao;
import com.bikemaintapp.Bike.Maintenance.App.models.data.ComponentDao;
import com.bikemaintapp.Bike.Maintenance.App.models.data.RideDao;
import com.bikemaintapp.Bike.Maintenance.App.models.data.UserDao;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

import java.util.List;

// Bike controller for creating a viewing bikes
@Controller
@RequestMapping("bike")
public class
BikeController extends com.bikemaintapp.Bike.Maintenance.App.controllers.Controller {

    @Autowired // Create an instance of this class
    private BikeDao bikeDao;
    @Autowired // Instance of the user class
    private UserDao userDao;
    @Autowired
    private ComponentDao componentDao;
    @Autowired
    private RideDao rideDao;

    // display all the Existing bike
    @RequestMapping(value="")
    public String index(Model model, HttpServletRequest request){

        if(notAuthenticated(request))
            return "redirect:/user/login";

        // User object
        User user = (User) request.getSession().getAttribute("user"); // Gets the user object from the session object
        // User flow
        model.addAttribute("title",user.getName() + "'s Bikes"); // Display the user name on the title page
        // Bike Flow
        model.addAttribute("bikes", bikeDao.findBikeByUser_Id(user.getId())); // Gets all the bikes of the current session user
        return "bike/index";
    }

    // Display the bike add page and creates a bike object
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddBikeForm(Model model, HttpServletRequest request){
        if(notAuthenticated(request))
            return "redirect:/user/login";

        model.addAttribute("title", "Add Bike");
        model.addAttribute(new Bike());
        return "bike/add";
    }

    // This view process the form from the bike form
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddBikeForm(@ModelAttribute @Valid Bike newBike, Errors errors, Model model,HttpServletRequest request){

        // If the value is not met then return user to the add page
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Bike"); // Pass this title to the view
            model.addAttribute("bike",newBike);
            return "bike/add";
        }
        // If the values are met the process form and return the new to the index view
        User user = (User) request.getSession().getAttribute("user"); // Get the session user
        newBike.setUser(user);

        //create default list of components when creating a new bike
        ArrayList<Component> defaultComponents = new ArrayList<>();
        defaultComponents.add(new Component(ComponentType.BRAKES));
        defaultComponents.add(new Component(ComponentType.DRIVETRAIN));
        defaultComponents.add(new Component(ComponentType.SUSPENSION));
        defaultComponents.add(new Component(ComponentType.TIRES));
        defaultComponents.add(new Component(ComponentType.FRAME));
        defaultComponents.add(new Component(ComponentType.WHEELS));

        newBike.setComponents(defaultComponents);
        bikeDao.save(newBike);

        model.addAttribute("bike",newBike); // Pass bike object into the view
        return "redirect:/bike/main/" + newBike.getId();
    }

    @RequestMapping(value = "main/{bikeId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int bikeId, HttpServletRequest request) {

        if(notAuthenticated(request))
            return "redirect:/user/login";

        Bike bike = bikeDao.findOne(bikeId); // Gets only one bike, filtered by the id
        model.addAttribute("title", bike.getNameOfBike()); // sends the bike object into the view.
        model.addAttribute("bike", bike);
        return "bike/main";
    }

    @RequestMapping(value = "edit/{bikeId}", method = RequestMethod.GET)
    public String editForm(Model model, @PathVariable int bikeId, HttpServletRequest request) {

        if(notAuthenticated(request))
            return "redirect:/user/login";

        Bike bike = bikeDao.findOne(bikeId);
        model.addAttribute("title", bike.getNameOfBike());
        model.addAttribute("bike", bike);
        return "bike/edit";
    }

    @RequestMapping(value = "edit/{bikeId}", method = RequestMethod.POST)
    public String editPost(Model model, @Valid Bike newBike, BindingResult bindingResult,
                           @PathVariable int bikeId, String nameOfBike) {

        if(bindingResult.hasErrors()){
            model.addAttribute("title", nameOfBike);
            model.addAttribute("bike", bikeDao.findOne(bikeId));
            model.addAttribute("nameError", "Name must be between 1 and 15 characters long.");
            return "bike/edit";
        }

        Bike bike = bikeDao.findOne(bikeId);
        bike.setNameOfBike(nameOfBike);
        bikeDao.save(bike);

        model.addAttribute("title", bike.getNameOfBike());
        model.addAttribute("bike", bike);
        return "bike/edit";
    }

    @RequestMapping(value = "delete/{bikeId}", method = RequestMethod.GET)
    public String deleteBike(Model model, @PathVariable int bikeId, HttpServletRequest request) {

        if(notAuthenticated(request))
            return "redirect:/user/login";

        Bike bike = bikeDao.findOne(bikeId);
        User user = bike.getUser();
        List<Bike> userBikes = user.getBikes();

        //remove bike from user list of bikes, but don't actually delete from DB
        //"oldUser" is set for a bike that has been deleted so that it can be re-used if user wants it back
        userBikes.remove(bike);
        user.setBikes(userBikes);
        userDao.save(user);
        bike.setOldUserId(user.getId());
        bike.setUser(null);
        bikeDao.save(bike);

        model.addAttribute("title",user.getName() + "'s Bikes");
        model.addAttribute("bikes", bikeDao.findBikeByUser_Id(user.getId()));
        return "redirect:/bike";
    }
}
