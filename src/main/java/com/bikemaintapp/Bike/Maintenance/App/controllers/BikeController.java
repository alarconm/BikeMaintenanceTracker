package com.bikemaintapp.Bike.Maintenance.App.controllers;


import com.bikemaintapp.Bike.Maintenance.App.models.Bike;
import com.bikemaintapp.Bike.Maintenance.App.models.User;
import com.bikemaintapp.Bike.Maintenance.App.models.data.BikeDao;
import com.bikemaintapp.Bike.Maintenance.App.models.data.UserDao;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
public class BikeController extends com.bikemaintapp.Bike.Maintenance.App.controllers.Controller {

    @Autowired // Create an instance of this class
    private BikeDao bikeDao;

    @Autowired // Instance of the user class
    private UserDao userDao;

    // display all the Existing bike
    @RequestMapping(value="")
    public String index(Model model, HttpServletRequest request){

        if(notAuthenticated(request))
            return "redirect:/user/login";
        // User object
        User sessionUserInfo = (User) request.getSession().getAttribute("user"); // Gets the user object from the session object
        // User flow
        model.addAttribute("title",sessionUserInfo.getName()); // Display the user name on the title page

        // Bike Flow
        model.addAttribute("bikes", bikeDao.findBikeByUser_Id(sessionUserInfo.getId())); // Gets all the bikes of the current session user
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
            return "bike/add";
        }
        // If the values are met the process form and return the new to the index view
        model.addAttribute("bike",newBike);
        User user = (User) request.getSession().getAttribute("user");
        newBike.setUser(user);
        model.addAttribute("title","View Bikes");
        bikeDao.save(newBike);
        return "redirect:";

    }
    // Direct users to individual bikes in the view
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String displayIndividualBikes(Model model){
        return "bike/main";
    }

}
