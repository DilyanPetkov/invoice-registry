package com.registry.cloudgateway.controller;

import com.registry.cloudgateway.dto.ClientDTO;
import com.registry.cloudgateway.dto.InvoiceDTO;
import com.registry.cloudgateway.dto.InvoiceSearchRequestDTO;
import com.registry.cloudgateway.dto.RestResponsePage;
import com.registry.cloudgateway.service.CloudGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CloudGatewayController {

    @Autowired
    private CloudGatewayService cloudGatewayService;

    @GetMapping("/clients/{id}")
    public ClientDTO getClientById(@PathVariable("id") String clientNumber) {
        return cloudGatewayService.findOneByClientNumber(clientNumber);
    }

    @GetMapping("/clients")
    public ResponseEntity<RestResponsePage<ClientDTO>> getAllClients(@RequestParam(value = "page", required = false) Integer page,
                                                                     @RequestParam(value = "size", required = false) Integer size) {
        return cloudGatewayService.getAllClients(page, size);
    }

    @PostMapping("/clients")
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        return cloudGatewayService.createClient(clientDTO);
    }

    @GetMapping("/invoices/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable("id") Long id) {
        return cloudGatewayService.getInvoiceById(id);
    }

    @GetMapping("/invoices")
    public ResponseEntity<RestResponsePage<InvoiceDTO>> getAllInvoices(@RequestParam(value = "page", required = false) Integer page,
                                                                       @RequestParam(value = "size", required = false) Integer size) {
        return cloudGatewayService.getAllInvoices(page, size);
    }

    @PostMapping("/invoices")
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return cloudGatewayService.createInvoice(invoiceDTO);
    }

    @PostMapping("/invoices/search")
    public List<InvoiceDTO> searchInvoice(@RequestBody InvoiceSearchRequestDTO invoiceSearchRequestDTO) {
        return cloudGatewayService.searchInvoice(invoiceSearchRequestDTO);
    }

}
