package com.authenticate.FoodOrdering.config;

import com.authenticate.FoodOrdering.security.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
@Configuration
@EnableWebSecurity

public class SecurityWebApplicationInitializer
            extends AbstractSecurityWebApplicationInitializer {
    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
    }

