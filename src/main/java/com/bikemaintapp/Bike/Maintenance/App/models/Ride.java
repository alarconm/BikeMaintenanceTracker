package com.bikemaintapp.Bike.Maintenance.App.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Ride {

    @Id
    @GeneratedValue
    private int id;

    //@NotNull
    private String nameOfRide;

    //min should be enforced as greater than 0 but @Range will only take ints
    //and plenty of valid rides could be under a mile
    @NotNull
    @Range(min = 0,max = 5000, message = "Ride must be between 0 and 5000 miles")
    private double miles;

    // Relationships
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bike_id")
    private Bike bike;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Ride(){
    }

    // TODO some kind of generated name for rides if user doesnt enter one (use DateTime()?)
    public Ride(String nameOfRide, Bike bike) {
        this.nameOfRide = nameOfRide;
        this.bike = bike;
    }

    public double getMiles(){
        return miles;
    }
    public void setMiles(Double miles){
        this.miles = miles;
    }
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
    public Bike getBike(){
        return bike;
    }
    public void setBike(Bike bike){
        this.bike = bike;
    }
}
