package com.bikemaintapp.Bike.Maintenance.App.validations;

import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(securedEnabled=true)
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private myAppUserDetailsService userDetailsService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/app/secure/**").hasAnyRole("ADMIN","USER")
                    .and().formLogin()  //login configuration
                    .loginPage("/user/login")
                    .loginProcessingUrl("/user/login")
                    .usernameParameter("name")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/user")
                    .and().logout()    //logout configuration
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/user")
                    .and().exceptionHandling() //exception handling configuration
                    .accessDeniedPage("/user/error");
        }
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }

}
