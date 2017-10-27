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

    //Not sure if we will let user enter miles,
    //or pull it from Bike.milesTraveled
    private int milesTraveled;

    // Relationships
    // There are many components, on one bike.
    @ManyToOne(cascade = {CascadeType.ALL})
    private Bike bike;

    //set up relationship with maintenance schedule based on component type
    @OneToOne(cascade = {CascadeType.ALL})
    private MaintenanceSchedule maintenanceSchedule;

    //DB holds link url text to image
    private String image;

    // Constructors
    // Default constructors required for Springboot/Hibernate
    public Component() {
    }

    public Component(int milesTraveled) {
        //this();
        //this.componentType = componentType;
        this.milesTraveled = milesTraveled;
    }

    public Component(ComponentType type) {
        this.type = type;
        this.setImageByType(type);
        //Create the maintenance schedule based on what type of component it is
        setMaintenanceSchedule(type);
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
                maintenanceSchedule.addInterval(100,"Clean thoroughly and inspect for any cracks");
                maintenanceSchedule.addInterval(500,"Tear down frame, clean and inspect for cracks, lube/grease all parts");
                break;

            case TIRES:
                maintenanceSchedule.addInterval(100,"check pressure and inspect for any cracks");
                maintenanceSchedule.addInterval(2500,"Replace tires if tread is fully worn down");
                break;

            case BRAKES:
                maintenanceSchedule.addInterval(100,"Check brake cables, increase tension if needed");
                maintenanceSchedule.addInterval(500,"Lubricate cables, increase tension if needed");
                maintenanceSchedule.addInterval(2500,"Check brake pads and replace if worn out");
                break;

            case WHEELS:
                maintenanceSchedule.addInterval(500,"Check spoke tension. Clean and inspect wheels for cracks. If cracked replace wheels.");
                break;

            case DRIVETRAIN:
                maintenanceSchedule.addInterval(100,"Lube chain, cassette and pedals");
                maintenanceSchedule.addInterval(500,"Perform full cleaning of chain and cassette. Re-lube all parts");
                maintenanceSchedule.addInterval(5000,"Replace chain and cassette");
                break;

            case SUSPENSION:
                maintenanceSchedule.addInterval(100,"Clean and lubricate suspension");
                maintenanceSchedule.addInterval(500,"Full suspension teardown and rebuild");
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
                this.setImage("/images/Drivetrain.jpeg");
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
}




