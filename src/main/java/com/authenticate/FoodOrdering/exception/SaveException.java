package com.authenticate.FoodOrdering.exception;

import com.authenticate.FoodOrdering.utils.encryption.ResponseCodes;
import org.springframework.http.HttpStatus;

public class SaveException extends Exception{

    private ResponseCodes responseCodes;
    private String messsage;

private HttpStatus httpStatus;
    public SaveException(ResponseCodes responseCodes, String message,HttpStatus httpStatus){
       this.responseCodes= responseCodes;
        this.messsage= message;
        this.httpStatus=httpStatus;
    }
}
