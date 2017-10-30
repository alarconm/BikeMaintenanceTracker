package com.bikemaintapp.Bike.Maintenance.App.strava;

public class Token {

    private String access_token;


    public Token() {}
    public Token(String token) {
        this.access_token = token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
