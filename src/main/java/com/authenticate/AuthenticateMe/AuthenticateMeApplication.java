package com.authenticate.AuthenticateMe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration(
		exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class AuthenticateMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticateMeApplication.class, args);
	}

}
