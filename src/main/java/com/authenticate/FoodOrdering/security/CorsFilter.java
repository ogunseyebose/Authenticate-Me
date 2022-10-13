package com.authenticate.FoodOrdering.security;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

public class CorsFilter {
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
        CorsConfiguration config= new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter();
    }
}
