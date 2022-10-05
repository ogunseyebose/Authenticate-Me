package com.authenticate.FoodOrdering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
@EnableJpaRepositories("com.authenticate.FoodOrdering.repository")


public class AuthenticateMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticateMeApplication.class, args);
	}

}
