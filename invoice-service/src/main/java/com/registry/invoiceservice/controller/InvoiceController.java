package com.registry.invoiceservice.controller;

import com.registry.invoiceservice.dto.InvoiceDTO;
import com.registry.invoiceservice.dto.InvoiceSearchRequestDTO;
import com.registry.invoiceservice.exception.InvoiceSearchException;
import com.registry.invoiceservice.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{id}")
    public InvoiceDTO getById(@PathVariable("id") Long id) {
        log.info("Inside getById method");
        return invoiceService.getInvoiceById(id);
    }

    @GetMapping
    public Page<InvoiceDTO> findAllInvoices(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "size", required = false) Integer size) {
        log.info("Inside findAllInvoices method");
        return invoiceService.getAllInvoices(page, size);
    }

    @PostMapping
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        log.info("Inside createInvoice method");
        return invoiceService.createInvoice(invoiceDTO);
    }

    @PostMapping("/search")
    public List<InvoiceDTO> getInvoicesByCriteria(@RequestBody InvoiceSearchRequestDTO criteriaDto) throws InvoiceSearchException {
        log.info("Inside getInvoicesByCriteria method");
        return invoiceService.getInvoicesByCriteria(criteriaDto);
    }

}
