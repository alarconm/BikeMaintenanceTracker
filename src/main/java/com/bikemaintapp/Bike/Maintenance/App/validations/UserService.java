package com.bikemaintapp.Bike.Maintenance.App.validations;

import com.bikemaintapp.Bike.Maintenance.App.models.User;

public interface UserService {
    User findUserByName(String name);
    void saveUser(User user);
}