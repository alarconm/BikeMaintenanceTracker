package com.bikemaintapp.Bike.Maintenance.App.models;


//TODO make this an abstract class that all types of bikes can model from

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bike {

    // Fields
    // This is the primary key for the bike class
    @Id
    @GeneratedValue // Auto generates the primary key
    private int id;

    @NotNull
    @Size(min = 3, max = 15, message = "Bike needs to be 3-15 characters long")
    private String nameOfBike;

    @NotNull
    @Range(min = 1, message = "please enter distance traveled in miles")
    private int milesTraveled;

    // Relationships
<<<<<<< HEAD
    // There is one bike to every user, This is the unique id for users to add a bike to their account
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
    private User user;

    @OneToMany(mappedBy = "bike",fetch = FetchType.EAGER)
    private List<Ride> rides;

    @OneToMany(mappedBy = "bike")
    private List<Component> components;

    public void setComponents(List<Component> components){
        this.components = components;
    }
    public List<Component> getComponents(){
        return this.components;
    }
    public void setUser(User user){
        this.user = user;
    }
    public User getUser(){
        return this.user;
    }
=======
    // A user can have many bikes, but one user
    @ManyToOne
    private User user;

    // There  are many components to a single bike.
    @OneToMany
    @JoinColumn(name = "bike_id")
    private List<Component> component = new ArrayList<>();
>>>>>>> refs/remotes/origin/master

    // Constructors
    // Default constructors required for Springboot/Hibernate
    public Bike() {
    }

    public Bike(String nameOfBike, int milesTraveled) {
        this.nameOfBike = nameOfBike;
        this.milesTraveled = milesTraveled;
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

    public int getMilesTraveled() {
        return milesTraveled;
    }

    public void setMilesTraveled(int milesTraveled) {
        this.milesTraveled = milesTraveled;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
