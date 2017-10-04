package com.bikemaintapp.Bike.Maintenance.App.validations;

import com.bikemaintapp.Bike.Maintenance.App.models.User;
import com.bikemaintapp.Bike.Maintenance.App.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginHelper {

    @Autowired
    private UserDao userdao;

    private Iterable<User> users;

    public LoginHelper() {

        this.users = userdao.findAll();
    }

    public int findUserByName(User findUser) {

        for(User user : users) {
            if (findUser.getName().equals(user.getName())) {
                return user.getId();
            }
        }
        return 0;
    }
}
