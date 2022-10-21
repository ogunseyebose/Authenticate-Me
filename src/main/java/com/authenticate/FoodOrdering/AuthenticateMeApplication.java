package com.authenticate.FoodOrdering;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories("com.authenticate.FoodOrdering.repository")
@ComponentScan("com.authenticate.FoodOrdering")
//@EnableScheduling
public class AuthenticateMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticateMeApplication.class, args);
	}
	@Bean

	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
