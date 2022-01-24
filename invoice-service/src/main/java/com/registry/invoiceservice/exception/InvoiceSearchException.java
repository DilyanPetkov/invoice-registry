package com.registry.invoiceservice.exception;

public class InvoiceSearchException extends Throwable {
    public InvoiceSearchException(){
        super("Invoice search with criterias not found");
    }
}
