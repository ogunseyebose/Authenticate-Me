package com.authenticate.FoodOrdering.security;

import com.authenticate.FoodOrdering.model.User;
import com.authenticate.FoodOrdering.service.UserService;
import com.authenticate.FoodOrdering.service.implementation.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JWTUtils  {

    @Value("${expiration-Time}")
    private Integer EXPIRATION_TIME;

    @Value("${secret-key}")
    private String SECRET_KEY;

    private final UserServiceImpl userService;

    public String generateToken(User user){
        return createToken(user.getEmail());
    }

    public String createToken( String subject){
       return  Jwts.builder().setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME ))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }
    public Boolean validateJwtToken(String token) {
        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(username) && !isTokenExpired);
    }
    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userService.loadUserByUsername(getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

}
