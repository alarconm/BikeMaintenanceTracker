package com.bikemaintapp.Bike.Maintenance.App.controllers;

import com.bikemaintapp.Bike.Maintenance.App.models.Component;
import com.bikemaintapp.Bike.Maintenance.App.models.ComponentType;
import com.bikemaintapp.Bike.Maintenance.App.models.data.ComponentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;



// Bike controller for creating a viewing bikes
@Controller
@RequestMapping("component")
public class ComponentController {

    // Link the component DB to the bike
    @Autowired
    ComponentDao componentDao;

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
    public String processAddComponentForm(@ModelAttribute @Valid Component newComponent, Errors errors, Model model){


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

}
