package com.authenticate.FoodOrdering.exception;

import org.springframework.http.HttpStatus;

public class MailingException extends Exception{

    private HttpStatus httpStatus;
    private String message;
    private String code;

    public MailingException(String code,String message,HttpStatus httpStatus){
        this.code= code;
        this.message= message;
        this.httpStatus= httpStatus;
    }
}
