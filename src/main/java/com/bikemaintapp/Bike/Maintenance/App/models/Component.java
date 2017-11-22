package com.bikemaintapp.Bike.Maintenance.App.models;

import com.bikemaintapp.Bike.Maintenance.App.models.maintenance.*;
import org.hibernate.validator.constraints.Range;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

//A bike component, such as brakes or derailleur

@Entity
public class Component {

    // Fields
    // This is the primary key for the Component class
    @Id
    @GeneratedValue // Auto generates the primary key
    private int id;

    //flag for the view to see if component currently needs maintenance or not
    private boolean needsMaintenance = false;

    //ENUMS for frame, chain, etc
    private ComponentType type;

    //Lifetime miles on the component
    private int componentMiles;

    // Relationships
    // There are many components, on one bike.
    @ManyToOne(cascade = {CascadeType.ALL})
    private Bike bike;

    //set up relationship with maintenance schedule based on component type
    @OneToOne(cascade = {CascadeType.ALL})
    private MaintenanceSchedule maintenanceSchedule;

    //DB holds link url text to image
    private String image;

    private String video;

    // Constructors
    // Default constructors required for Springboot/Hibernate
    public Component() {
    }

    public Component(ComponentType type) {
        this.type = type;
        this.setImageByType(type); //this doesn't work...is the type not set yet?
        //Create the maintenance schedule based on what type of component it is
        this.setMaintenanceSchedule(type); //same as the image...
    }

    // Setters & Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComponentMiles() {
        return this.componentMiles;
    }

    public void setComponentMiles(int componentMiles) {
        this.componentMiles = componentMiles;
    }

    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public boolean getNeedsMaintenance() {
        return needsMaintenance;
    }

    public void setNeedsMaintenance(boolean needsMaintenance) {
        this.needsMaintenance = needsMaintenance;
    }

    public MaintenanceSchedule getMaintenanceSchedule() {
        return maintenanceSchedule;
    }

    //Set the maintenance schedule based on what type of component it is
    public void setMaintenanceSchedule(ComponentType componentType) {
        this.maintenanceSchedule = new MaintenanceSchedule();
        switch (componentType) {
            case FRAME:
                maintenanceSchedule.addInterval(
                        100,"Clean thoroughly and inspect for any cracks",
                        "https://www.youtube.com/embed/Z4KPijfR1Q8?rel=0");
                maintenanceSchedule.addInterval(
                        500,
                        "Tear down frame, clean and inspect for cracks, lube/grease all parts",
                        "https://www.youtube.com/embed/zYDx7mAhq7Q?rel=0");
                break;

            case TIRES:
                maintenanceSchedule.addInterval(
                        100,
                        "check pressure and inspect for any cracks",
                        "https://www.youtube.com/embed/FNU0RwAWL4c?rel=0");
                maintenanceSchedule.addInterval(
                        2500,
                        "Replace tires if tread is fully worn down",
                        "https://www.youtube.com/embed/sGdu4fkrQ9M?rel=0");
                break;

            case BRAKES:
                maintenanceSchedule.addInterval(
                        100,
                        "Check brake cables, increase tension if needed",
                        "https://www.youtube.com/embed/hg6s596PPRY?rel=0");
                maintenanceSchedule.addInterval(
                        500,
                        "Lubricate cables, increase tension if needed",
                        "https://www.youtube.com/embed/iYiCm-W-nO0?rel=0");
                maintenanceSchedule.addInterval(
                        2500,
                        "Check brake pads and replace if worn out",
                        "https://www.youtube.com/embed/Y5FBygoegYQ?rel=0");
                break;

            case WHEELS:
                maintenanceSchedule.addInterval(
                        500,
                        "Check spoke tension. Clean and inspect wheels for cracks. If cracked replace wheels.",
                        "https://www.youtube.com/embed/9sWQ9lKGdRo?rel=0");
                break;

            case DRIVETRAIN:
                maintenanceSchedule.addInterval(
                        100,
                        "Lube chain, cassette and pedals",
                        "https://www.youtube.com/embed/YOZLa539wd0?rel=0");
                maintenanceSchedule.addInterval(
                        500,
                        "Perform full cleaning of chain and cassette. Re-lube all parts",
                        "https://www.youtube.com/embed/OvKcsMg8TJc?rel=0");
                maintenanceSchedule.addInterval(
                        5000,
                        "Replace chain and cassette",
                        "https://www.youtube.com/embed/Zo-8ofvdxQo?rel=0");
                break;

            case SUSPENSION:
                maintenanceSchedule.addInterval(
                        100,
                        "Clean and lubricate suspension",
                        "https://www.youtube.com/embed/dmOzeFItkd8?rel=0");
                maintenanceSchedule.addInterval(
                        500,
                        "Full suspension teardown and rebuild",
                        "https://www.youtube.com/embed/wxma5WJEZiI?rel=0");
                break;

            default:
                this.maintenanceSchedule = new MaintenanceSchedule();
                break;
        }
        maintenanceSchedule.setComponent(this);

    }

    public boolean isNeedsMaintenance() {
        return needsMaintenance;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // add default pictures when the bike is created
    public void setImageByType(ComponentType componentType) {

        switch (componentType) {
            case FRAME:
                this.setImage("/images/Frame.jpeg");
                break;

            case SUSPENSION:
                this.setImage("/images/Suspension.jpeg");
                break;

            case DRIVETRAIN:
                this.setImage("/images/DriveTrain.jpeg");
                break;

            case WHEELS:
                this.setImage("/images/Wheel.jpeg");
                break;

            case BRAKES:
                this.setImage("/images/Brakes.jpeg");
                break;

            case TIRES:
                this.setImage("/images/Tires.jpeg");
                break;
        }
    }

    // returns the maintenance interval with the lowest amount of mileage until maintenance is due
    public MaintInterval getLowestMaintInterval() {

        MaintInterval lowestInterval = this.getMaintenanceSchedule().getIntervals().get(0);

        for(MaintInterval interval : this.getMaintenanceSchedule().getIntervals()) {

            if (interval.getIntervalMiles() < lowestInterval.getIntervalMiles()) {
                lowestInterval = interval;
            }
        }
    return lowestInterval;
    }

    public void addMiles(int miles) {
        this.componentMiles += miles;
    }
}




