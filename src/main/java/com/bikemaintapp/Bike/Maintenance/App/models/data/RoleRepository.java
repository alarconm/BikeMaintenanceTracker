package com.bikemaintapp.Bike.Maintenance.App.models.data;

import com.bikemaintapp.Bike.Maintenance.App.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByRole(String role);

}