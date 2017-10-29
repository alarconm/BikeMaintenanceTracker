package com.bikemaintapp.Bike.Maintenance.App.strava;

import org.springframework.web.client.RestTemplate;

public class Authentication {

    RestTemplate restTemplate = new RestTemplate();
    StravaRide ride = restTemplate.getForObject("https://www.strava.com/oauth/authorize?" +
            "client_id=21098&response_type=code&redirect_uri=http://127.0.0.1", StravaRide.class);


}

