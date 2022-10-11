package com.authenticate.FoodOrdering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.authenticate.FoodOrdering.repository")

@EnableScheduling
public class AuthenticateMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticateMeApplication.class, args);
	}

}
