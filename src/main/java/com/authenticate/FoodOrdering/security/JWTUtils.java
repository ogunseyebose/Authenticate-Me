package com.authenticate.FoodOrdering.security;

import com.authenticate.FoodOrdering.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import static com.authenticate.FoodOrdering.security.SecurityConstants.EXPIRATION_TIME;
import static com.authenticate.FoodOrdering.security.SecurityConstants.SECRET_KEY;


public class JWTUtils  {

    public String generateToken(User user){
        //Map<String, Object> claims= new HashMap<>();
        return createToken(user.getEmail());
    }

    public String createToken( String subject){
       return  Jwts.builder().setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME ))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }
    public Boolean validateJwtToken(String token, User user) {
        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(user.getUsername()) && !isTokenExpired);
    }
    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
