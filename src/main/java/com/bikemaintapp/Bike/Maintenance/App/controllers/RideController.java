package com.bikemaintapp.Bike.Maintenance.App.controllers;

import com.bikemaintapp.Bike.Maintenance.App.models.Ride;
import com.bikemaintapp.Bike.Maintenance.App.models.User;
import com.bikemaintapp.Bike.Maintenance.App.models.data.BikeDao;
import com.bikemaintapp.Bike.Maintenance.App.models.data.RideDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("ride")
public class RideController extends com.bikemaintapp.Bike.Maintenance.App.controllers.Controller {

    @Autowired
    RideDao rideDao;
    @Autowired
    BikeDao bikeDao;

    // Display list of rides sorted by date
    @RequestMapping(value = "")
    @Required
    public String index(Model model, HttpServletRequest request){

        if(notAuthenticated(request))
            return "redirect:/user/login";

        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("rides",rideDao.findRideByUserId(user.getId()));

        model.addAttribute("title",user.getName() + "'s Rides");
        return "ride/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddRideForm(Model model, HttpServletRequest request){

        //Kick them out if not logged in
        if(notAuthenticated(request))
            return "redirect:/user/login";
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("bikes",bikeDao.findBikeByUser_Id(user.getId()));
        //model.addAttribute("bikes",user.getBikes()); //this one wouldnt show bikes added in current session..?
        model.addAttribute(new Ride());
        return "ride/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddRideForm(@ModelAttribute @Valid Ride newRide, Errors errors, Model model,HttpServletRequest request){

        if(errors.hasErrors()){
            return "redirect:ride/add";
        }
        model.addAttribute("ride",newRide);
        User user = (User) request.getSession().getAttribute("user");
        newRide.setUser(user);

        //Add miles from ride to bike
        newRide.getBike().addMiles((int)newRide.getMiles());

        rideDao.save(newRide);
        return "redirect:";
    }
}
