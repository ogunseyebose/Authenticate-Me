package com.authenticate.FoodOrdering.security;

import com.authenticate.FoodOrdering.repository.UserRepo;
import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;


//@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
   JWTUtils jwtUtils;
    @Autowired
    UserRepo userRepo;

    public JwtTokenFilter(JWTUtils jwtUtils) {
        this.jwtUtils= jwtUtils;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header= request.getHeader(HttpHeaders.AUTHORIZATION);
        if(isEmpty(header) || !header.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        final String token= header.split(" ")[1].trim();
        if(!jwtUtils.validateJwtToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        List<String> list= new ArrayList<>();
    UserDetails userDetails= userRepo.findByEmail(jwtUtils.getUsernameFromToken(token)).orElse(null);
        UsernamePasswordAuthenticationToken authentication= new UsernamePasswordAuthenticationToken(userDetails==null? list:list, userDetails,
                null);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
}
}

