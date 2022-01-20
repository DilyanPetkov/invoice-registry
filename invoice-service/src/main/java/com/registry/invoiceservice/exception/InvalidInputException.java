package com.registry.invoiceservice.exception;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String message){
        super(String.format("Invalid input for field with value " + message));
    }
}
