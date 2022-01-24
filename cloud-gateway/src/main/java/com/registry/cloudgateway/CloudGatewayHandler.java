package com.registry.cloudgateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CloudGatewayHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) {
        return generateErrorResponse( ex.getResponseBodyAsString(),
                ex.getStatusCode());
    }

    private static ResponseEntity<String> generateErrorResponse(String message,HttpStatus status){
        return new ResponseEntity<String>(message,status);
    }
}
