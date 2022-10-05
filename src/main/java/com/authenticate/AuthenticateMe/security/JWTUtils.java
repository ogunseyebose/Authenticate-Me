package com.authenticate.AuthenticateMe.security;

import com.authenticate.AuthenticateMe.model.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


public class JWTUtils  {

    private final String SECRET_KEY= "+[opkkgioi095ikfjji8439405-";
    public String generateToken(User user){
        //Map<String, Object> claims= new HashMap<>();
        return createToken(user.getEmail());
    }

    public String createToken( String subject){
       return  Jwts.builder().setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*1))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }
}
