package com.bikemaintapp.Bike.Maintenance.App.models.data;


import com.bikemaintapp.Bike.Maintenance.App.models.Component;
import com.bikemaintapp.Bike.Maintenance.App.models.ComponentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public interface ComponentDao extends CrudRepository<Component,Integer> {
}

