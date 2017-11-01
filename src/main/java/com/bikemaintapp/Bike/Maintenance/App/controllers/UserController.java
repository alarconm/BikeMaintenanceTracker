package com.bikemaintapp.Bike.Maintenance.App.controllers;

import com.bikemaintapp.Bike.Maintenance.App.models.User;
import com.bikemaintapp.Bike.Maintenance.App.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "")
    public String index() {
        return "redirect:/user/login";
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

        //loop through all users in the database
        for (User user : userDao.findAll()) {
            //Check to see if username that was entered is in the database
            //Redirect with error back to login page if user does not exist
            if (newUser.getName().equals(user.getName())) {

                //once you have found a valid username that matches what the user entered to login
                //now check the password that they entered to make sure it matches the password on the
                //user that was found in the database **I MADE USERNAMES UNIQUE so no duplicates can happen**
                //We can change this at a later date to just login with password or whatever and get rid of username
                if (newUser.getPassword().equals(user.getPassword())) {

                    //If user name is found in database and the password entered matches the password of that user
                    //place the loggedInUser object named "user" into the session
                    User loggedInUser = userDao.findOne(user.getId());
                    request.getSession().setAttribute("user", loggedInUser);
                    return "redirect:/bike";

                    //TODO find the best way to do this
                    /////////////////////////////////////////////////////////////////////////////////
                    // example of how you can GET THE CURRENTLY LOGGED IN USER FROM SESSION        //
                    // it casts the session object into a User object then looks in the            //
                    // database to find the user from the database once you have that user         //
                    // you can send that user object to a form to use that information             //
                    // **Get the session object and turn it into a User object**                   //
                    // User usertest = (User) request.getSession().getAttribute("user");           //
                    // **Find the user in the database based on the ID from the object in session  //
                    //  User userFoundInDatabase = userDao.findOne(usertest.getId());              //
                    /////////////////////////////////////////////////////////////////////////////////

                } else {

                    //crappy validation and manual error thrown if the password doesnt match
                    //reloads the page if invalid
                    model.addAttribute("title", "Login");
                    model.addAttribute("passworderror", "Password is incorrect");
                    return "user/login";
                }
            }
        }

        //validation and manual error thrown if the username is not found in the database
        //reloads the page if invalid
        model.addAttribute("title", "Login");
        model.addAttribute("usererror", "User does not exist");
        return "user/login";

    }

    //form to create a new user
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddUserForm(Model model) {

        model.addAttribute("title", "Register");
        model.addAttribute(new User());
        return "user/add";
    }

    private boolean userNameTaken(String username){
        User user = userDao.findUserByName(username);

        if(user != null){
            return true;
        }
        return false;
    }
    //HttpServletRequest is for session management
    //process the user creation form
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddUserForm(@ModelAttribute @Valid User newUser, Errors errors,
                                     Model model, HttpServletRequest request, String verifyPassword) {

        if (errors.hasErrors() || !verifyPassword.equals(newUser.getPassword()) || userNameTaken(newUser.getName())) {
            model.addAttribute("title", "Create New Account");
            model.addAttribute("user", newUser);

            if (!verifyPassword.equals(newUser.getPassword())) {
                model.addAttribute("passwordError",
                        "You must enter the same password both times");
            }
            if (userNameTaken(newUser.getName())){
                model.addAttribute("nameError",
                        "Username unavailable");
            }
            return "user/add";
        }

        userDao.save(newUser);
        model.addAttribute("user", newUser);
        model.addAttribute("title", "User Login");

        // Successful create then adds the username to the session
        request.getSession().setAttribute("user", newUser);
        return "redirect:/bike";

    }

    //clicking the "logout" link will log the user out and clear them from the session
    //redirects to home page after logout
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logUserOut(HttpServletRequest request) {

        //removes user from session
        request.getSession().removeAttribute("user");
        return "redirect:";
    }


}
