package com.bikemaintapp.Bike.Maintenance.App.controllers;
import com.bikemaintapp.Bike.Maintenance.App.models.User;

import javax.servlet.http.HttpServletRequest;

public abstract class Controller {

     //returns true if user is NOT authenticated
     boolean notAuthenticated(HttpServletRequest request){
        try{
            User user = (User) request.getSession().getAttribute("user");
            if(user == null){
                return true;
            }
            //return false if everything is OK
            return false;
        }
        //If any error, return true ie Not Authenticated
        catch (Exception e){
            return true;
        }
    }

}
