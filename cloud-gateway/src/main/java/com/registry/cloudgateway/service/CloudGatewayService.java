package com.registry.cloudgateway.service;

import com.registry.cloudgateway.dto.ClientDTO;
import com.registry.cloudgateway.dto.InvoiceDTO;
import com.registry.cloudgateway.dto.RestResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CloudGatewayService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${clients.url}")
    private String clientUrl;

    @Value("${invoices.url}")
    private String invoiceUrl;

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        return restTemplate.postForObject(invoiceUrl, invoiceDTO, InvoiceDTO.class);
    }

    public ResponseEntity<RestResponsePage<InvoiceDTO>> getAllInvoices(Integer page, Integer size) {
        ParameterizedTypeReference<RestResponsePage<InvoiceDTO>> responseType = new ParameterizedTypeReference<>() {
        };

        if(page==null || size==null){
            return restTemplate.exchange(invoiceUrl + "/", HttpMethod.GET, null, responseType);
        }

        return restTemplate.exchange(invoiceUrl + "?page=" + page + "&size=" + size, HttpMethod.GET, null, responseType);
    }

    public InvoiceDTO getInvoiceById(Integer id) {
        return restTemplate.getForObject(invoiceUrl + "/" + id, InvoiceDTO.class);
    }

    public ClientDTO findOneByClientNumber(String clientNumber) {
        return restTemplate.getForObject(clientUrl + "/" + clientNumber, ClientDTO.class);
    }

    public ResponseEntity<RestResponsePage<ClientDTO>> getAllClients(Integer page, Integer size) {
        ParameterizedTypeReference<RestResponsePage<ClientDTO>> responseType = new ParameterizedTypeReference<>() {
        };
        if(page==null || size==null){
            return restTemplate.exchange(clientUrl + "/", HttpMethod.GET, null, responseType);
        }
        return restTemplate.exchange(clientUrl + "?page=" + page + "&size=" + size, HttpMethod.GET, null, responseType);
    }

    public ClientDTO createClient(ClientDTO client) {
        return restTemplate.postForObject(clientUrl, client, ClientDTO.class);
    }

}
