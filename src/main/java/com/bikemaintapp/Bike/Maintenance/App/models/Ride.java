package com.bikemaintapp.Bike.Maintenance.App.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Ride {

    @Id
    @GeneratedValue
    private int id;

    //Ride needs a bikeID
    @NotNull
    private int bikeID;

    @NotNull
    private String nameOfRide;

    //min should be enforced as greater than 0 but @Range will only take ints
    //and plenty of valid rides could be under a mile
    @NotNull
    @Range(min = 0,max = 9999, message = "")
    private double miles;

    public Ride(){
    }

    // TODO some kind of generated name for rides if user doesnt enter one
    public Ride(String nameOfRide,String nameOfBike) {
        this.nameOfRide = nameOfRide;
        //fetch bikeID from bikename
    }

    public Ride(String nameOfRide,int bikeID){
        this.nameOfRide = nameOfRide;
        this.bikeID = bikeID;
    }
}
