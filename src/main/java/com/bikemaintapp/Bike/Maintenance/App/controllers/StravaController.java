package com.bikemaintapp.Bike.Maintenance.App.controllers;

import com.bikemaintapp.Bike.Maintenance.App.models.Bike;
import com.bikemaintapp.Bike.Maintenance.App.models.Component;
import com.bikemaintapp.Bike.Maintenance.App.models.Ride;
import com.bikemaintapp.Bike.Maintenance.App.models.User;
import com.bikemaintapp.Bike.Maintenance.App.models.data.BikeDao;
import com.bikemaintapp.Bike.Maintenance.App.models.data.RideDao;
import com.bikemaintapp.Bike.Maintenance.App.models.data.UserDao;
import com.bikemaintapp.Bike.Maintenance.App.models.forms.AddStravaRideForm;
import com.bikemaintapp.Bike.Maintenance.App.strava.StravaRide;
import com.bikemaintapp.Bike.Maintenance.App.strava.Token;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("strava")
public class StravaController extends com.bikemaintapp.Bike.Maintenance.App.controllers.Controller {

    @Autowired
    RideDao rideDao;
    @Autowired
    BikeDao bikeDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String stravaGet(Model model, @RequestParam("code") String code, HttpServletRequest request) {

        if(notAuthenticated(request))
            return "redirect:/user/login";

        User user = (User) request.getSession().getAttribute("user");

        //Post to the strava api with client ID, client secret and the code received as a param from the
        //authentication request that redirected to here. With those three things this post request then
        //gets a "Token" that gives us access to that specific strava user
        RestTemplate restTemplate = new RestTemplate();
        Token responseToken = new Token();
        Token token = restTemplate.postForObject("https://www.strava.com/oauth/token?client_id=21098&" +
                "client_secret=38d841bdc3a4f03ceab757cc6671277697f8e499&code=" + code, responseToken ,Token.class);

        //Get all activities for the authenticated strava user as an array of StravaRide objects
        RestTemplate restTemplate1 = new RestTemplate();
        StravaRide[] rides = restTemplate1.getForObject(
                "https://www.strava.com/api/v3/athlete/activities?&access_token="
                        +token.getAccess_token(), StravaRide[].class);

        //TODO refactor this flow to check if the user has already added these specific strava rides
        //loop through each strava activity that we just got from the api and create a new ride for each
        //activity that is a "Ride" (users can have runs,swims etc)
        //Distance comes in as meters so it is converted to miles
        //mileage for each ride is then added to the components on the bike
        ArrayList<StravaRide> stravaRides = new ArrayList<>();

        for (StravaRide ride : rides) {
            if (ride.getType().equals("Ride")) {
                int rideMiles = (int)Math.round((ride.getDistance()*0.000621371)*10); //convert meters to miles
                ride.setMiles((double)rideMiles / 10); //truncate the double down to one decimal place
                stravaRides.add(ride);
            }

            //check to see if the user already has imported and saved these rides
            //Only display strava rides that are new
            if (!user.getRides().isEmpty()) {
                for (Ride userRide : user.getRides()) {
                    if (userRide.getStravaId() == ride.getId()) {
                        stravaRides.remove(ride);
                    }
                }
            }
        }

        user.setStravaRides(stravaRides); // add all of the stravaRides to the current user
        AddStravaRideForm rideForm = new AddStravaRideForm(bikeDao.findBikeByUser_Id(user.getId()), stravaRides);

        model.addAttribute("form", rideForm); //form adds the bikes and stravarides for the user
        model.addAttribute("title", user.getName() + "'s Rides");
        return "ride/stravaAdd";
    }

    //Post from the strava ride list - user checks which ones get sent here to be added
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String stravaPost(Model model, @ModelAttribute @Valid AddStravaRideForm stravaRideForm,
                             @RequestParam int[] rideId, @RequestParam int bikeId,
                             HttpServletRequest request, Errors errors) {

        if(notAuthenticated(request))
            return "redirect:/user/login";

        if (errors.hasErrors()) {
            model.addAttribute("rideForm", stravaRideForm);
            return "ride/stravaAdd";
        }
        User user = (User) request.getSession().getAttribute("user");

        //Iterate through each ride ID that was selected - set the user, set the miles
        for (int id : rideId) {
            Ride addRide = new Ride(user.getStravaRideById(id).getName(), bikeDao.findOne(bikeId));
            addRide.setUser(user);
            addRide.setMiles(user.getStravaRideById(id).getMiles());
            addRide.setStravaId(id); //add strava id to the ride - used to check if its from strava or manual

            //Iterate through each component on the bike selected and update the mileage on each based on the ride
            List<Component> components = addRide.getBike().getComponents();
            for (int i = 0; i < components.size(); i++) {
                components.get(i).getMaintenanceSchedule().addMiles((int)addRide.getMiles());
            }
            rideDao.save(addRide);
        }

        model.addAttribute("rides", rideDao.findRideByUserId(user.getId()));
        model.addAttribute("title", user.getName() + "'s Rides");
        return "ride/index";
    }
}
