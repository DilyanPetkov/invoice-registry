package com.registry.clientservice.exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String message){
        super("Client with id "+message+" not found");
    }
}
