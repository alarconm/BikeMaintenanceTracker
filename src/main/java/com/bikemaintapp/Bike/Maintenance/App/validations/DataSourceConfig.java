//package com.bikemaintapp.Bike.Maintenance.App.validations;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class DataSourceConfig extends WebMvcConfigurerAdapter{
//
//    @Bean(name = "dataSource")
//    public DriverManagerDataSource datasource() {
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        driverManagerDataSource.setUrl("jdbc:mysql://localhost:8889/bikemaintapp");
//        driverManagerDataSource.setUsername("bikemaintapp");
//        driverManagerDataSource.setPassword("bike");
//        return driverManagerDataSource;
//    }
//}
