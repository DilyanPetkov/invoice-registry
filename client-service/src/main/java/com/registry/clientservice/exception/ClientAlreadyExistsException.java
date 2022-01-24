package com.registry.clientservice.exception;

public class ClientAlreadyExistsException extends RuntimeException{
    public ClientAlreadyExistsException(String message){
        super(String.format("Client with client number "+ message +" already exists"));
    }
}
