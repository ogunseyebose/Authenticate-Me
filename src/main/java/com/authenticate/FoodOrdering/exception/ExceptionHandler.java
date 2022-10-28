package com.authenticate.FoodOrdering.exception;

import com.authenticate.FoodOrdering.dto.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MailingException.class)
    public ResponseEntity<Response> handleMailing(MailingException ex,
                                           HttpServletRequest request, HttpServletResponse response) {
        log.error("Exception occurred while processing the request: {} because: {} ", request.getRequestURI(), response.getStatus());

        return ResponseEntity.internalServerError().body((new Response("99", "Email couldn't be sent successfully")));
    }
    public ResponseEntity<Response> handleSaving(SaveException ex,
                                           HttpServletRequest request, HttpServletResponse response) {
        log.error("Exception occurred while saving : {} because: {} ", request.getRequestURI(), response.getStatus());

        return ResponseEntity.internalServerError().body((new Response("99", ex.getMessage())));
    }
    public ResponseEntity<Response> handleGeneric(GenericException ex,
                                                  HttpServletRequest request, HttpServletResponse response) {
        log.error("Exception occurred while processing the request: {} because: {}" , request.getRequestURI(), response.getStatus());

        return ResponseEntity.internalServerError().body((new Response("99", ex.getMessage())));
    }
    public ResponseEntity<Response> handle(BadRequestException ex,
                                                  HttpServletRequest request, HttpServletResponse response) {
        log.error("Exception occurred while processing the request: {} because: {} ", request.getRequestURI(), response.getStatus());

        return ResponseEntity.internalServerError().body((new Response("99", ex.getMessage())));
    }
}
