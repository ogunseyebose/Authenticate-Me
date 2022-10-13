package com.authenticate.FoodOrdering.security;

import com.authenticate.FoodOrdering.model.User;
import com.authenticate.FoodOrdering.repository.UserRepo;
import com.authenticate.FoodOrdering.service.UserService;
import com.authenticate.FoodOrdering.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //private final UserDetailsService userDetailsService;
    //private  final UserServiceImpl userService;
    private final UserRepo userRepo;
    private final JWTUtils jwtUtils;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
@Bean
    public ModelMapper modelmapper(){
        return new ModelMapper();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/signUp**","/dashboard","/login")
                .permitAll() .anyRequest().permitAll()
                .and()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
                .and().apply(new JwtConfigurer(jwtUtils));
        ;

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        auth.userDetailsService(email ->userRepo.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException(format("Email: %s, not found", email))) );
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

}
