package com.bikemaintapp.Bike.Maintenance.App.models.data;

import com.bikemaintapp.Bike.Maintenance.App.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

}
