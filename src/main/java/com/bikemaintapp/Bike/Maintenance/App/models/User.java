package com.bikemaintapp.Bike.Maintenance.App.models;

import com.bikemaintapp.Bike.Maintenance.App.strava.StravaRide;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    //TODO add real validations to user fields

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    @NotNull
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long")
    private String name;

    @NotNull
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters long")
    private String password;

    @NotNull
    private String email;

    private ArrayList<StravaRide> stravaRides;

    @OneToMany(mappedBy = "user")
    private List<Bike> bikes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ride> rides;

    public void setBikes(List<Bike> bikes){
        this.bikes = bikes;
    }

    //Empty constructor needed for some Springboot/Hibernate magic
    public User() {}

    public User(String name) {
        this.name = name.toLowerCase();
    }

    public List<Ride> getRides(){
        return rides;
    }
    public void setRides(List<Ride> rides){
        this.rides = rides;
    }

    public List<Bike> getBikes(){
        return bikes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<StravaRide> getStravaRides() {
        return stravaRides;
    }

    public void setStravaRides(ArrayList<StravaRide> stravaRides) {
        this.stravaRides = stravaRides;
    }

    public StravaRide getStravaRideById(int id) {
        for (StravaRide ride : this.stravaRides) {
            if (ride.getId() == id) {
                return ride;
            }
        }
        return null;
    }
}
