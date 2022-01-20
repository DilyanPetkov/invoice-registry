package com.registry.invoiceservice.exception;

public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException(String message){
        super(String.format("Invoice with id "+ message +" already exists"));
    }
}
