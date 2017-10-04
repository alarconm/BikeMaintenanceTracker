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
    // There is one bike to every user, This is the unique id for users to add a bike to their account
    @OneToMany
    @JoinColumn(name ="bike_id")
    private List<User> users = new ArrayList<>();

    // Constructors
    // Default constructors required for Springboot/Hibernate
    public Bike() {
    }

    public Bike(String nameOfBike) {
        this.nameOfBike = nameOfBike;
    }

    // Setters & Getters
    public int getId() {
        return id;
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
}
