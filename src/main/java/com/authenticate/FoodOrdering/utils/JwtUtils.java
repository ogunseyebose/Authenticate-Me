package com.authenticate.FoodOrdering.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.authenticate.FoodOrdering.model.Token;
import com.authenticate.FoodOrdering.security.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtils {
	
	private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    public static DecodedJWT Decode(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(SecurityConstants.ISSUER)
                .build(); //Reusable verifier instance
        //Invalid signature/claims
        return verifier.verify(token);
    }
    
    public Token createToken(String clientId) {
    	logger.info("Creating Json Web Token for client: {}",clientId);
    	
    	String token = JWT.create().withIssuer(SecurityConstants.ISSUER)
    			.withSubject(clientId)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET));
    	logger.info("token"+token);
    	logger.info("Json Web Token created successfully for client: {}",clientId);
    	return new Token(token,"bearer", SecurityConstants.EXPIRATION_TIME);
    }
}
