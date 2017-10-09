package com.bikemaintapp.Bike.Maintenance.App.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Range(min = 0,max = 9999, message = "")
    private double miles;


    // Relationships
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bike_id")
    private Bike bike;

    //This is temporary i think. trying to figure out how to select bike object from add form
   /* @NotNull
    private int bikeWhich;

    public int getBikeWhich(){
        return  bikeWhich;
    }
    public void setBikeWhich(int bikeWhich){
        this.bikeWhich = bikeWhich;
    }
*/
    public Ride(){
    }

    // TODO some kind of generated name for rides if user doesnt enter one
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
    public Bike getBike(){
        return bike;
    }
    public void setBike(Bike bike){
        this.bike = bike;
    }
}
