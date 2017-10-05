package com.bikemaintapp.Bike.Maintenance.App.models.data;


import com.bikemaintapp.Bike.Maintenance.App.models.Component;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface ComponentDao extends CrudRepository<Component,Integer> {
}

