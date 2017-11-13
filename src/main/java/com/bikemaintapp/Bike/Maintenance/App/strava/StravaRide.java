package com.bikemaintapp.Bike.Maintenance.App.strava;

import com.bikemaintapp.Bike.Maintenance.App.models.data.RideDao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StravaRide{

    private int id;
    private float distance;
    private double miles;
    private String name;
    private Object gear;
    private String type;
    private String description;
    private String code;

    public StravaRide() {}

    public StravaRide(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getGear() {
        return gear;
    }

    public void setGear(Object gear) {
        this.gear = gear;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public void setDescription(String description) {
        this.description = description;



    }
}
