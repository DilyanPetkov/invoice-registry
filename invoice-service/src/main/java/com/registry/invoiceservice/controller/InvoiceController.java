package com.registry.invoiceservice.controller;

import com.registry.invoiceservice.entity.Invoice;
import com.registry.invoiceservice.service.InvoiceService;
import com.registry.invoiceservice.valobject.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{id}")
    public Invoice getById(@PathVariable("id") Integer id){
        return invoiceService.getInvoiceById(id);
    }

    @GetMapping("/all")
    public List<Invoice> findAllInvoices(){
        return invoiceService.getAllInvoices();
    }

    @PostMapping("/create")
    public Invoice createInvoice(@RequestBody Invoice invoice){
        return invoiceService.createInvoice(invoice);
    }

    @GetMapping("/{clientNumber}")
    public ResponseObject getInvoiceByClientNumber(@PathVariable("clientNumber") String clientNumber) {
        return invoiceService.getInvoiceByClientNumber(clientNumber);
    }
}
