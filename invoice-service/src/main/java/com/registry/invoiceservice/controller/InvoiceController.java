package com.registry.invoiceservice.controller;

import com.registry.invoiceservice.dto.InvoiceSearchRequestDTO;
import com.registry.invoiceservice.dto.InvoiceDTO;
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
    public InvoiceDTO getById(@PathVariable("id") Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @GetMapping
    public Page<InvoiceDTO> findAllInvoices(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "size", required = false) Integer size) {
        return invoiceService.getAllInvoices(page, size);
    }

    @PostMapping
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.createInvoice(invoiceDTO);
    }

    @PostMapping("/search")
    public List<InvoiceDTO> getInvoicesByCriteria(@RequestBody InvoiceSearchRequestDTO criteriaDto) {
        return invoiceService.getInvoicesByCriteria(criteriaDto);
    }

}
