package com.bikemaintapp.Bike.Maintenance.App.models.forms;

import com.bikemaintapp.Bike.Maintenance.App.models.Bike;
import com.bikemaintapp.Bike.Maintenance.App.strava.StravaRide;

import javax.validation.constraints.NotNull;

public class AddStravaRideForm {

    private Iterable<Bike> bikes;
    private Iterable<StravaRide> stravaRides;

    @NotNull
    private int bikeId;

    @NotNull
    private int stravaRideId;

    public AddStravaRideForm(Iterable<Bike> bikes, Iterable<StravaRide> stravaRides) {
        this.bikes = bikes;
        this.stravaRides = stravaRides;
    }
    public AddStravaRideForm() {}

    public Iterable<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(Iterable<Bike> bikes) {
        this.bikes = bikes;
    }

    public Iterable<StravaRide> getStravaRides() {
        return stravaRides;
    }

    public void setStravaRides(Iterable<StravaRide> stravaRides) {
        this.stravaRides = stravaRides;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public int getStravaRideId() {
        return stravaRideId;
    }

    public void setStravaRideId(int stravaRideId) {
        this.stravaRideId = stravaRideId;
    }
}
