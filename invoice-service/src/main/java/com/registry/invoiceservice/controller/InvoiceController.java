package com.registry.invoiceservice.controller;

import com.registry.invoiceservice.entity.Invoice;
import com.registry.invoiceservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/invoices")
    public Page<Invoice> findAllInvoices(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "size", required = false) Integer size){
        return invoiceService.getAllInvoices(page, size);
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody Invoice invoice){
        return invoiceService.createInvoice(invoice);
    }

}
