package com.authenticate.FoodOrdering.exception;

public class CustomAuthenticationException extends  RuntimeException{

    public CustomAuthenticationException(String message){
        super(message);
    }
}
