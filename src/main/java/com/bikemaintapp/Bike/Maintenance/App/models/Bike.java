package com.bikemaintapp.Bike.Maintenance.App.models;


//TODO make this an abstract class that all types of bikes can model from

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Iterator;
import java.util.List;

@Entity
public class Bike {

    // Fields
    // This is the primary key for the bike class
    @Id
    @GeneratedValue // Auto generates the primary key
    private int id;

    @NotNull
    @Size(min = 1, max = 15, message = "Bike name needs to be 1-15 characters long")
    private String nameOfBike;

    // Relationships

    // There is one bike to every user, This is the unique id for users to add a bike to their account
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
    private User user;

    @OneToMany(mappedBy = "bike",fetch = FetchType.EAGER)
    private List<Ride> rides;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name ="bike_id")
    private List<Component> components;

    private boolean needsMaintenance = false;

    // Constructors
    // Default constructors required for Springboot/Hibernate
    public Bike() {
    }

    public Bike(String nameOfBike) {
        this.nameOfBike = nameOfBike;
    }

    //One option for tracking miles
    //Should have less potential for errors
    public int countMiles(){

        Iterator iterator = rides.iterator();
        int total = 0;
        while(iterator.hasNext()) {
            Ride tempRide = (Ride) iterator.next();
            total += (int) tempRide.getMiles();
        }
        return total;
    }

    //Temporary function for me to check on whats going on without real debug
    public void printBikeName(){
        System.out.println(this.nameOfBike);
        System.out.println(countMiles());
    }
    //Called by /ride/add to add mileage.
    //This function should be able to pass this onto the bikes Component list.
    public void addMiles(int miles){
        //this.milesTraveled += miles;
    }

    //used to add a component to the bike
    public void addComponent(Component component) {
        components.add(component);
    }

    // Setters & Getters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfBike() {
        return nameOfBike;
    }

    public void setNameOfBike(String nameOfBike) {
        this.nameOfBike = nameOfBike;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setComponents(List<Component> components){
        this.components = components;
    }
    public List<Component> getComponents(){
        return this.components;
    }
    public User getUser(){
        return this.user;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public boolean isNeedsMaintenance() {
        return needsMaintenance;
    }

    public void setNeedsMaintenance(boolean needsMaintenance) {
        this.needsMaintenance = needsMaintenance;
    }
}
