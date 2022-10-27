package com.authenticate.FoodOrdering.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private JwtAuthenticationEntryPoint unauthorizedHandler;

	public WebSecurity(JwtAuthenticationEntryPoint unauthorizedHandler) {
		this.unauthorizedHandler = unauthorizedHandler;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.headers().xssProtection()
				.and().contentSecurityPolicy("'default-src \\'self\\'; img-src https://*; child-src \\'none\\''")
				.and().cacheControl()
				.and().contentTypeOptions()
				.and().frameOptions().sameOrigin()
				.and().authorizeRequests()
				.and().authorizeRequests()
				.antMatchers("/api/v1/user/signUp","/webjars/**","/api/v1/user/login", "/swagger-ui*/**", "/v3/api-docs/**","/api/v1/orders/dashboard").permitAll()
				.anyRequest().authenticated().and()
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
				// this disables session creation on Spring Security
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}



	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}



}