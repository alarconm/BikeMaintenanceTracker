package com.bikemaintapp.Bike.Maintenance.App.models.data;


import com.bikemaintapp.Bike.Maintenance.App.models.Bike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface BikeDao extends CrudRepository<Bike,Integer> {
}
