package com.registry.invoiceservice.exception;

public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException(String message){
        super("Invoice with id "+ message +" not found");
    }
}
