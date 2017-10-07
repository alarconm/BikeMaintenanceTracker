package com.bikemaintapp.Bike.Maintenance.App.models.data;

import com.bikemaintapp.Bike.Maintenance.App.models.User;

public interface IUserInfoDao {
        User getActiveUser(String userName);
}
