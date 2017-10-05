package com.bikemaintapp.Bike.Maintenance.App.controllers;

import com.bikemaintapp.Bike.Maintenance.App.models.User;
import com.bikemaintapp.Bike.Maintenance.App.models.data.UserDao;
import com.mysql.fabric.xmlrpc.base.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "User Login");
        return "user/index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {

        model.addAttribute("title", "Login");
        model.addAttribute(new User());
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(@ModelAttribute @Valid User newUser, Errors errors,
                                   Model model, HttpServletRequest request) {


        for (User user : userDao.findAll()) {
            //Check to see if username is in the database
            //Redirect with error back to login page if user does not exist
            if (newUser.getName().equals(user.getName())) {

                if (newUser.getPassword().equals(user.getPassword())) {

                    User loggedInUser = userDao.findOne(user.getId());
                    request.getSession().setAttribute("user", loggedInUser);


//TODO find the best way to do this
//                    User usertest = (User) request.getSession().getAttribute("user");
//                    userDao.findOne(usertest.getId());


                    return "redirect:/bike";


                } else {

                    model.addAttribute("title", "Login");
                    model.addAttribute("passworderror", "Password is incorrect");
                    return "user/login";

                }
            }
        }

        model.addAttribute("title", "Login");
        model.addAttribute("usererror", "User does not exist");
        return "user/login";




    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddUserForm(Model model) {

        model.addAttribute("title", "Create New Account");
        model.addAttribute(new User());

        return "user/add";
    }

    //
    //HttpServletRequest is for session management
    //
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddUserForm(@ModelAttribute @Valid User newUser, Errors errors,
                                     Model model, HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create New Account");
            model.addAttribute("user", newUser);
            return "user/add";
        }

        userDao.save(newUser);
        model.addAttribute("user", newUser);
        model.addAttribute("title", "User Login");

        //Adds the username to the session
        request.getSession().setAttribute("user", newUser);
        return "user/index";

    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logUserOut(HttpServletRequest request) {

        request.getSession().removeAttribute("user");
        return "redirect:";
    }


}
