package com.authenticate.FoodOrdering.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {
    private final JWTUtils jwtUtils;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token =jwtUtils.resolveToken((HttpServletRequest) servletRequest);
        if (token != null && jwtUtils.validateJwtToken(token)) {
            Authentication auth = token != null ? jwtUtils.getAuthentication(token) : null;
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
    }

