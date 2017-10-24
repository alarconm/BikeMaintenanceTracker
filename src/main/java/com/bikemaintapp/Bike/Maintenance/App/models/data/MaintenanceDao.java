package com.bikemaintapp.Bike.Maintenance.App.models.data;


import com.bikemaintapp.Bike.Maintenance.App.models.Bike;
import com.bikemaintapp.Bike.Maintenance.App.models.Ride;
import com.bikemaintapp.Bike.Maintenance.App.models.maintenance.MaintenanceSchedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MaintenanceDao extends CrudRepository<MaintenanceSchedule,Integer> {

}