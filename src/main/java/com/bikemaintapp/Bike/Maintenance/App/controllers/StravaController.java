package com.bikemaintapp.Bike.Maintenance.App.controllers;

import com.bikemaintapp.Bike.Maintenance.App.models.Bike;
import com.bikemaintapp.Bike.Maintenance.App.models.Component;
import com.bikemaintapp.Bike.Maintenance.App.models.Ride;
import com.bikemaintapp.Bike.Maintenance.App.models.User;
import com.bikemaintapp.Bike.Maintenance.App.models.data.BikeDao;
import com.bikemaintapp.Bike.Maintenance.App.models.data.RideDao;
import com.bikemaintapp.Bike.Maintenance.App.models.data.UserDao;
import com.bikemaintapp.Bike.Maintenance.App.strava.StravaRide;
import com.bikemaintapp.Bike.Maintenance.App.strava.Token;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("strava")
public class StravaController extends com.bikemaintapp.Bike.Maintenance.App.controllers.Controller {

    @Autowired
    RideDao rideDao;
    @Autowired
    BikeDao bikeDao;
    @Autowired
    UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayLoginForm(Model model, @RequestParam("code") String code, HttpServletRequest request) {

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

        //loop through each strava activity that we just got from the api and create a new ride for each
        //activity that is a "Ride"
        //Distance comes in as meters so it is converted to miles
        //mileage for each ride is then added to the components on the bike
        for (StravaRide ride : rides) {
            if (ride.getType().equals("Ride")) {

                //TODO use a dropdown or button to set which bike is used
                //for testing it is just getting the first bike in the users list of bikes
                Bike currentBike = bikeDao.findBikeByUser_Id(user.getId()).get(0);
                Ride addRide = new Ride(ride.getName(), currentBike);

                //Truncate the crazy double down for viewing pleasure
                int rideMiles = (int)Math.round((ride.getDistance()*0.000621371)*10);
                addRide.setMiles((double)rideMiles / 10);
                addRide.setUser(user);
                List<Component> components = addRide.getBike().getComponents();

                for (int i = 0; i < components.size(); i++) {
                    components.get(i).getMaintenanceSchedule().addMiles((int)addRide.getMiles());
                }
                rideDao.save(addRide);
            }
        }

        model.addAttribute("rides",rideDao.findRideByUserId(user.getId()));
        model.addAttribute("title",user.getName() + "'s Rides");
        return "ride/index";
    }


}
