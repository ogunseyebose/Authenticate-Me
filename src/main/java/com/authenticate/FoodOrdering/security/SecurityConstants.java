package com.authenticate.FoodOrdering.security;

public class SecurityConstants {
	static final String SECRET_KEY= "+[opkkgioi095ikfjji8439405-";

	public static final String ISSUER = "Ecobank";
	    public static final long EXPIRATION_TIME = 3600000; // 1hr
	    public static final String TOKEN_PREFIX = "Bearer ";
	    public static final String HEADER_STRING = "Authorization";
	    public static final String GET_AUTH_TOKEN = "/api/v1/token";
	    public static final String LOGIN = "/api/v1/user/login";

}