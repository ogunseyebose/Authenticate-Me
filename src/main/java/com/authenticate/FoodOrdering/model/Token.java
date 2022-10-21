package com.authenticate.FoodOrdering.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Token {

	
	public Token(String accessToken, String tokenType, long expiresIn) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.expiresIn = expiresIn;
	}
	
	public Token() {}
	
	
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("expires_in")
	private long expiresIn;

	
}
