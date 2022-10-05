package com.authenticate.AuthenticateMe.security;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
@Bean
    public ModelMapper modelmapper(){
        return new ModelMapper();
    }
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    public SecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http
                .csrf().disable()
                .authorizeRequests().antMatchers("/signUp")
                .permitAll() .anyRequest().authenticated()
                .and()
                //.formLogin() .loginPage("/login")
                //.permitAll()
//                .and()
                .logout() .invalidateHttpSession(true)
                .clearAuthentication(true) .permitAll();*/
        http
                .cors()
                .and().csrf().disable()
                //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .headers()
                .httpStrictTransportSecurity().includeSubDomains(true)
                .maxAgeInSeconds(31536000)
                .and().xssProtection()
                .and().contentSecurityPolicy("'default-src \\'self\\'; img-src https://*; child-src \\'none\\''")
                .and().cacheControl()
                .and().contentTypeOptions()
                .and().httpStrictTransportSecurity()
                .and().frameOptions().sameOrigin()
                .and().authorizeRequests()
                .and().authorizeRequests()
                .antMatchers("/api/v1/signUp","/api/v1/login")
                .permitAll()
                .anyRequest().authenticated()
                //.and().addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth){

    }
*/
}
