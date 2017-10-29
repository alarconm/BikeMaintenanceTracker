package com.bikemaintapp.Bike.Maintenance.App.controllers;

import com.bikemaintapp.Bike.Maintenance.App.models.User;
import com.bikemaintapp.Bike.Maintenance.App.strava.StravaRide;
import com.bikemaintapp.Bike.Maintenance.App.strava.Token;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("strava")
public class StravaController {

        @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayLoginForm(Model model, @RequestParam("code") String code) {

        model.addAttribute("title", "Login");
        model.addAttribute(new User());


        //strava testing
        RestTemplate restTemplate = new RestTemplate();
        Token responseToken = new Token();
        Token token = restTemplate.postForObject("https://www.strava.com/oauth/token?client_id=21098&" +
                "client_secret=38d841bdc3a4f03ceab757cc6671277697f8e499&code=" + code, responseToken ,Token.class);

        RestTemplate restTemplate1 = new RestTemplate();

        StravaRide rides = restTemplate1.getForObject("https://www.strava.com/api/v3/athlete/activities?access_token="
                +token, StravaRide.class);

        return "user/login";
    }


}
