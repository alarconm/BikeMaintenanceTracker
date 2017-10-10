package com.bikemaintapp.Bike.Maintenance.App.models;

/**
 * Created by Timothy on 10/7/2017.
 */
public enum ComponentType {


    //Add any number of commonly maintained bike components to this list
    //At some point, provision needs to be made for
    //user entering a component that is not on the list

    //Initially these will be in a checkbox form
    //but may eventually be clicked on if we develop a
    //graphical representation of a bike


    FRAME ("Frame"),
    BRAKES ("Brakes"),
    CHAIN ("Chain");

    private final String name;

    ComponentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}