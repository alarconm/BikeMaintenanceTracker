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



<<<<<<< HEAD
    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select name, password, verify_password from user where name=?");
//                .authoritiesByUsernameQuery("u.name, r.role from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.name=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user", "/user/add").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .usernameParameter("name").passwordParameter("password")
                .and()
                .logout()
                .and()
                .csrf().disable();

    }
=======
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
>>>>>>> 7a4770c309824b58bf41b7fac10845c28f1f2452

}
