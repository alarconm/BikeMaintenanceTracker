package com.bikemaintapp.Bike.Maintenance.App.models.data;

import com.bikemaintapp.Bike.Maintenance.App.models.Ride;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RideDao extends CrudRepository<Ride, Integer>{
}
