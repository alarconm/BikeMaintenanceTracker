package com.bikemaintapp.Bike.Maintenance.App.models;

import com.bikemaintapp.Bike.Maintenance.App.models.data.ComponentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//Class is used to create a default list of components for the first bike created.

@Repository
public class DefaultComponents {

    @Autowired
    private ComponentDao componentDao;

    //Create a set of generic components to be used when creating a bike for the first time
    public void saveDefaults() {

        //if there are no components in DB yet, create defaults
        if (componentDao.findOne(1) == null) {

            componentDao.save(new Component(ComponentType.BRAKES));
            componentDao.save(new Component(ComponentType.DRIVETRAIN));
            componentDao.save(new Component(ComponentType.WHEELS));
            componentDao.save(new Component(ComponentType.FRAME));
            componentDao.save(new Component(ComponentType.TIRES));
            componentDao.save(new Component(ComponentType.SUSPENSION));
        }
    }
}
