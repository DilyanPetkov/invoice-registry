package com.registry.cloudgateway.service;

import com.registry.cloudgateway.dto.ClientDTO;
import com.registry.cloudgateway.dto.InvoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CloudGatewayService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String CLIENT_URL = "http://localhost:8081/clients";
    private static final String INVOICE_URL = "http://localhost:8082/invoices";

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        return restTemplate.postForObject(INVOICE_URL + "/create", invoiceDTO, InvoiceDTO.class);
    }

    public List<InvoiceDTO> getAllInvoices() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(INVOICE_URL + "/all", InvoiceDTO[].class)));
    }

    public InvoiceDTO getInvoiceById(Integer id) {
        return restTemplate.getForObject(INVOICE_URL + "/" + id, InvoiceDTO.class);
    }

    public ClientDTO findOneByClientNumber(String clientNumber) {
        return restTemplate.getForObject(CLIENT_URL + "/" + clientNumber, ClientDTO.class);
    }

    public List<ClientDTO> getAllClients() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(CLIENT_URL + "/all", ClientDTO[].class)));
    }

    public ClientDTO createClient(ClientDTO client) {
        return restTemplate.postForObject(CLIENT_URL + "/create", client, ClientDTO.class);
    }

}
