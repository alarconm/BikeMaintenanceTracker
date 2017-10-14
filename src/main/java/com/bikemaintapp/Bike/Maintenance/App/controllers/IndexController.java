package com.bikemaintapp.Bike.Maintenance.App.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Redirect the user to UserController for the home page
@Controller
@RequestMapping("")
public class IndexController {

    @RequestMapping(value = "")
    public String index() {
        return "redirect:/user";
    }

}
