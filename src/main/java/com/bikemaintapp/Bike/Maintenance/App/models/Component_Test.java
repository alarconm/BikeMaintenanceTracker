package com.bikemaintapp.Bike.Maintenance.App.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Component_Test {

    //Fields
    @Id
    @GeneratedValue // Auto generates the primary key
    private int id;

    @NotNull
    private String typeOfComponent;

    @NotNull
    private int milesOnComponent;

    // Relationships
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bike_id")
    private Bike bike;

    @OneToMany(mappedBy = "bike")
    private List<Component_Test> components;
    // This will link the bike Id to the component

    //Constructors
    public Component_Test() {
    } // Required for Springboot/hibernate

    public Component_Test(String typeOfComponent, int milesOnComponent) {
        this.typeOfComponent = typeOfComponent;
        this.milesOnComponent = milesOnComponent;
    }

    //Setters & Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfComponent() {
        return typeOfComponent;
    }

    public void setTypeOfComponent(String typeOfComponent) {
        this.typeOfComponent = typeOfComponent;
    }

    public int getMilesOnComponent() {
        return milesOnComponent;
    }

    public void setMilesOnComponent(int milesOnComponent) {
        this.milesOnComponent = milesOnComponent;
    }
}
