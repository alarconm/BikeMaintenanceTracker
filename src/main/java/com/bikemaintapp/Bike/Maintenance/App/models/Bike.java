package com.bikemaintapp.Bike.Maintenance.App.models;


//TODO make this an abstract class that all types of bikes can model from

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
}
