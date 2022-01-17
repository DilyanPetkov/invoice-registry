package com.registry.cloudgateway.controller;

import com.registry.cloudgateway.dto.ClientDTO;
import com.registry.cloudgateway.dto.InvoiceDTO;
import com.registry.cloudgateway.service.CloudGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableEurekaClient
@RestController
public class CloudGatewayController {

    @Autowired
    private CloudGatewayService cloudGatewayService;

    @GetMapping("/clients/{id}")
    public ClientDTO getClientById(@PathVariable("id") String clientNumber) {
        return cloudGatewayService.findOneByClientNumber(clientNumber);
    }

    @GetMapping("/clients")
    public List<ClientDTO> getAllClients() {
        return cloudGatewayService.getAllClients();
    }

    @PostMapping("clients")
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        return cloudGatewayService.createClient(clientDTO);
    }

    @GetMapping("/invoices/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable("id") Integer id) {
        return cloudGatewayService.getInvoiceById(id);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getAllInvoices() {
        return cloudGatewayService.getAllInvoices();
    }

    @PostMapping("/invoices")
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return cloudGatewayService.createInvoice(invoiceDTO);
    }

}
