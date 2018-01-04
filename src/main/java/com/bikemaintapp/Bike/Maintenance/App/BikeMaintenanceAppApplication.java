package com.bikemaintapp.Bike.Maintenance.App;

import com.bikemaintapp.Bike.Maintenance.App.storage.StorageProperties;
import com.bikemaintapp.Bike.Maintenance.App.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class BikeMaintenanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeMaintenanceAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
