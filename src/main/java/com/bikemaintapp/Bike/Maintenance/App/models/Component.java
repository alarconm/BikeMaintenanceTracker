package com.bikemaintapp.Bike.Maintenance.App.models;

import com.bikemaintapp.Bike.Maintenance.App.models.maintenance.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
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
    @ManyToOne
    private Bike bike;

    //set up relationship with maintenance schedule based on component type
    @OneToOne(cascade = {CascadeType.ALL})
    private MaintenanceSchedule maintenanceSchedule;

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

        switch (componentType) {
            case FRAME:
                this.maintenanceSchedule = new FrameMaintenanceSchedule();
                break;

            case TIRES:
                this.maintenanceSchedule = new TiresMaintenanceSchedule();
                break;

            case BRAKES:
                this.maintenanceSchedule = new BrakesMaintenanceSchedule();
                break;

            case WHEELS:
                this.maintenanceSchedule = new WheelsMaintenanceSchedule();
                break;

            case DRIVETRAIN:
                this.maintenanceSchedule = new DriveTrainMaintenanceSchedule();
                break;

            case SUSPENSION:
                this.maintenanceSchedule = new SuspensionMaintenanceSchedule();
                break;

                default:
                    this.maintenanceSchedule = maintenanceSchedule;
                    break;
        }

    }
}




