package com.authenticate.FoodOrdering.security;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
@Bean
    public ModelMapper modelmapper(){
        return new ModelMapper();
    }
   // public  JwtAuthenticationEntryPoint unauthorizedHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/register**","/dashboard")
                .permitAll() .anyRequest().authenticated()
                .and()
                .formLogin() .loginPage("/login")
                .permitAll()
                .and()
                .logout() .invalidateHttpSession(true)
                .clearAuthentication(true) .permitAll();
    }
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth){

    }
*/
}
