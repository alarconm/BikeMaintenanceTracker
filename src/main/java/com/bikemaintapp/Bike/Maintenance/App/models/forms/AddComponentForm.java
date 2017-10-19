package com.bikemaintapp.Bike.Maintenance.App.models.forms;

import com.bikemaintapp.Bike.Maintenance.App.models.Bike;
import com.bikemaintapp.Bike.Maintenance.App.models.Component;

import javax.validation.constraints.NotNull;

public class AddComponentForm {

    private Bike bike;
    private Iterable<Component> components;

    @NotNull
    private int bikeId;

    @NotNull
    private int componentId;

    public AddComponentForm() { }

    public AddComponentForm(Iterable<Component> components, Bike bike) {
        this.components = components;
        this.bike = bike;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public Iterable<Component> getComponents() {
        return components;
    }

    public void setComponents(Iterable<Component> components) {
        this.components = components;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }


}
