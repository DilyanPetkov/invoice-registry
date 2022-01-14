package com.registry.cloudgateway.service;

import com.registry.cloudgateway.dto.ClientDTO;
import com.registry.cloudgateway.dto.InvoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CloudGatewayService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${clients.url}")
    private String clientUrl;

    @Value("${invoices.url}")
    private String invoiceUrl;

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        return restTemplate.postForObject(invoiceUrl + "/invoice", invoiceDTO, InvoiceDTO.class);
    }

    public List<InvoiceDTO> getAllInvoices() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(invoiceUrl + "/invoices", InvoiceDTO[].class)));
    }

    public InvoiceDTO getInvoiceById(Integer id) {
        return restTemplate.getForObject(invoiceUrl + "/" + id, InvoiceDTO.class);
    }

    public ClientDTO findOneByClientNumber(String clientNumber) {
        return restTemplate.getForObject(clientUrl + "/" + clientNumber, ClientDTO.class);
    }

    public List<ClientDTO> getAllClients() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(clientUrl + "/clients", ClientDTO[].class)));
    }

    public ClientDTO createClient(ClientDTO client) {
        return restTemplate.postForObject(clientUrl + "/client", client, ClientDTO.class);
    }

}
