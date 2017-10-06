package com.bikemaintapp.Bike.Maintenance.App.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//A bike component, such as brakes or derailleur

@Entity
public class Component {

    // Fields
    // This is the primary key for the Component class
    @Id
    @GeneratedValue // Auto generates the primary key
    private int id;

    @NotNull
    @Size(min = 3, max = 15, message = "Component name must be 3-15 characters long")
    private String componentName;

    //Lifetime miles on the component
    private int componentMiles;

    //Not sure if we will let user enter miles,
    //or pull it from Bike.milesTraveled
    @NotNull
    @Range(min = 1, message = "How many miles would you like to add to this component?")
    private int milesTraveled;

    // Relationships
    // There are many components, on one bike.
    @ManyToOne
    private Bike bike;

    // Constructors
    // Default constructors required for Springboot/Hibernate
    public Component() {
    }

    public Component(String componentName, int milesTraveled) {
        this.componentName = componentName;
        this.milesTraveled = milesTraveled;
    }

    // Setters & Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public int getComponentMiles() {
        return this.componentMiles;
    }

    public void setMilesTraveled(int milesTraveled) {
        this.milesTraveled = milesTraveled;
        this.componentMiles = this.componentMiles + this.milesTraveled;
    }

    public int getMilesTraveled() {
        return milesTraveled;
    }

    public void setComponentMiles(int componentMiles) {
        this.componentMiles = componentMiles;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
}
