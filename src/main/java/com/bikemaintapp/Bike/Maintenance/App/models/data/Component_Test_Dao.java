package com.bikemaintapp.Bike.Maintenance.App.models.data;

import com.bikemaintapp.Bike.Maintenance.App.models.Component_Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface Component_Test_Dao extends CrudRepository<Component_Test,Integer> {
}


