package com.bikemaintapp.Bike.Maintenance.App.controllers;

import com.bikemaintapp.Bike.Maintenance.App.models.Bike;
import com.bikemaintapp.Bike.Maintenance.App.models.Component;
import com.bikemaintapp.Bike.Maintenance.App.models.ComponentType;
import com.bikemaintapp.Bike.Maintenance.App.models.data.BikeDao;
import com.bikemaintapp.Bike.Maintenance.App.models.data.ComponentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


// Bike controller for creating a viewing bikes
@Controller
@RequestMapping("component")
public class ComponentController {

    // Link the component DB to the bike
    @Autowired
    ComponentDao componentDao;

    @Autowired
    BikeDao bikeDao;

    // display all the existing components
    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("components", componentDao.findAll()); // Displays all bikes to the view. // TODO display bikes of a user
        model.addAttribute("title","View Components");
        return "component/index";
    }

    // Display the Add Component page and create a component object
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddComponentForm(Model model){
        model.addAttribute("title", "Add Component");
        model.addAttribute(new Component());
        model.addAttribute("componentTypes", ComponentType.values());
        return "component/add";
    }

    // This view processes the component form
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddComponentForm(@ModelAttribute @Valid Component newComponent, Errors errors, Model model,
                                          List<Component> components){


        // If the value is not correct then return user to the add page
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Component"); // Pass this title to the view
            return "component/add";
        }
        // If the values are met then process form and return the new to the index view
        model.addAttribute("component",newComponent);
        model.addAttribute("title","View Components");
        componentDao.save(newComponent);
        return "component/index";

    }

    @RequestMapping(value = "add-component/{bikeId}", method = RequestMethod.GET)
    public String addComponent(Model model, @PathVariable int bikeId) {

        Bike bike = bikeDao.findOne(bikeId); //current user selected bike

        model.addAttribute("title", "Add a new component to " + bike.getNameOfBike());
        model.addAttribute(new Component()); //New component to be created
        model.addAttribute("types", ComponentType.values()); //enums of component types
        model.addAttribute("bikeId", bikeId); //bikeId is needed to post & save component to bike
        return "component/add-component";
    }

    @RequestMapping(value = "add-component", method = RequestMethod.POST)
    public String addComponent(Model model, @ModelAttribute @Valid Component component, Errors errors, int bikeId) {

        Bike bike = bikeDao.findOne(bikeId);

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add a new component to " + bike.getNameOfBike());
            model.addAttribute("component", component);
            model.addAttribute("bikeId", bikeId);
            model.addAttribute("types", ComponentType.values());
            return "add-component/{bikeId}";
        }
        Component newComponent = new Component(component.getType());
        newComponent.setMaintenanceSchedule(component.getType());
        bike.addComponent(newComponent);
        bikeDao.save(bike);
        return "redirect:/bike/main/" + bike.getId(); //Bike detailed view
    }

    @RequestMapping(value = "component-detail/{componentId}" )
    public String componentDetail(Model model, @PathVariable int componentId) {

        model.addAttribute("component", componentDao.findOne(componentId));
        return "component/component-detail";
    }

    @RequestMapping(value = "delete/{componentId}", method = RequestMethod.GET)
    public String deleteComponent(Model model, @PathVariable int componentId) {

        //look up the bike by the component, remove component from list of components on bike then save bike
        Component component = componentDao.findOne(componentId);
        Bike bike = component.getBike();
        List<Component> componentsOnBike = bike.getComponents();
        componentsOnBike.remove(component);
        bike.setComponents(componentsOnBike);
        bikeDao.save(bike);

        //old user ID set so that it can be accessed from an old components list if user wants to re-use
        component.setOldUserId(bike.getUser().getId());


        return "redirect:/bike/edit/" + bike.getId();
    }

}
