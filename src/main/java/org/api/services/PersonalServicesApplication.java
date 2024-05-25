package org.api.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonalServicesApplication extends ServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(PersonalServicesApplication.class, args);
	}

}
