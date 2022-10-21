package com.authenticate.FoodOrdering.security;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {

	    public static final String SECRET = "Ploofuhgghfytyehbcx09383774u8uhfueukjsjuu16354";
	    public static final String ISSUER = "Food Ordering Company";
	    public static final long EXPIRATION_TIME = 300000; // 5mins
	    public static final String TOKEN_PREFIX = "Bearer ";
	    public static final String HEADER_STRING = "Authorization";


}
