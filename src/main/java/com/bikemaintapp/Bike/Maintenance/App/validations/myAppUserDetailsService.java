package com.bikemaintapp.Bike.Maintenance.App.validations;

import com.bikemaintapp.Bike.Maintenance.App.models.User;
import com.bikemaintapp.Bike.Maintenance.App.models.data.IUserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class myAppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        User activeUserInfo = userInfoDao.getActiveUser(userName);
        GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());

        UserDetails userDetails = (UserDetails)new User(activeUserInfo.getName());
        return userDetails;
    }
}