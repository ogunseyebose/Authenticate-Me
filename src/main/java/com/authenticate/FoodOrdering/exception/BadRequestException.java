package com.authenticate.FoodOrdering.exception;

import com.authenticate.FoodOrdering.utils.encryption.ResponseCodes;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Data
@Accessors(chain = true)
public class BadRequestException extends Exception {

    private HttpStatus httpStatus;
    private ResponseCodes code;
    private String message;

    public BadRequestException(ResponseCodes code, String message, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
