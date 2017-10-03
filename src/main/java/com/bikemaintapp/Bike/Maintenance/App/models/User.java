package com.bikemaintapp.Bike.Maintenance.App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

    //TODO add real validations to user fields

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long")
    private String name;

    @NotNull
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters long")
    private String password;

    @NotNull
    private String verifyPassword;

    @NotNull
    private String email;

    //Empty contstructor needed for some Springboot/Hibernate magic
    public User() {}

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
