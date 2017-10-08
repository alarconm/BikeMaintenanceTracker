package com.bikemaintapp.Bike.Maintenance.App.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

//A bike component, such as brakes or derailleur

@Entity
public class Component {

    // Fields
    // This is the primary key for the Component class
    @Id
    @GeneratedValue // Auto generates the primary key
    private int id;

    /*

    //Let user enter name. Leaving this here temporarily
    //in case we go back to this instead of using ENUMS

    @NotNull
    @Size(min = 3, max = 15, message = "Component name must be 3-15 characters long")
    private String componentName;
    */



    //ENUMS for frame, chain, etc
    private ComponentType type;

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

    public Component(int milesTraveled) {
        //this();
        //this.componentType = componentType;
        this.milesTraveled = milesTraveled;
    }

    // Setters & Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*

    //Getters and Setters for componentName.
    //Left them here for now in case we go back to componentName
    //instead of ENUMS

    public String getComponentName() { return componentName;    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }
    */

    public int getComponentMiles() {
        return this.componentMiles;
    }

    public void setMilesTraveled(int milesTraveled) {
        this.milesTraveled = milesTraveled;
        this.componentMiles = this.componentMiles + this.milesTraveled;
    }

    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }
}
