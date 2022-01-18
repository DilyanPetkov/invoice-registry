package com.registry.cloudgateway.service;

import com.registry.cloudgateway.dto.ClientDTO;
import com.registry.cloudgateway.dto.CriteriaDTO;
import com.registry.cloudgateway.dto.InvoiceDTO;
import com.registry.cloudgateway.dto.RestResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CloudGatewayService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${clients.url}")
    private String clientUrl;

    @Value("${invoices.url}")
    private String invoiceUrl;

    private static final String SEARCH = "/search";
    private static final String SLASH = "/";
    private static final String PAGE = "?page=";
    private static final String SIZE = "&size=";

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        return restTemplate.postForObject(invoiceUrl, invoiceDTO, InvoiceDTO.class);
    }

    public List<InvoiceDTO> searchInvoice(CriteriaDTO criteriaDTO) {
        return Arrays.asList(restTemplate.postForObject(invoiceUrl + SEARCH, criteriaDTO, InvoiceDTO[].class));
    }

    public ResponseEntity<RestResponsePage<InvoiceDTO>> getAllInvoices(Integer page, Integer size) {
        ParameterizedTypeReference<RestResponsePage<InvoiceDTO>> responseType = new ParameterizedTypeReference<>() {
        };

        if (page == null || size == null) {
            return restTemplate.exchange(invoiceUrl + SLASH, HttpMethod.GET, null, responseType);
        }

        return restTemplate.exchange(invoiceUrl + PAGE + page + SIZE + size, HttpMethod.GET, null, responseType);
    }

    public InvoiceDTO getInvoiceById(Long id) {
        return restTemplate.getForObject(invoiceUrl + SLASH + id, InvoiceDTO.class);
    }

    public ClientDTO findOneByClientNumber(String clientNumber) {
        return restTemplate.getForObject(clientUrl + SLASH + clientNumber, ClientDTO.class);
    }

    public ResponseEntity<RestResponsePage<ClientDTO>> getAllClients(Integer page, Integer size) {
        ParameterizedTypeReference<RestResponsePage<ClientDTO>> responseType = new ParameterizedTypeReference<>() {
        };
        if (page == null || size == null) {
            return restTemplate.exchange(clientUrl + SLASH, HttpMethod.GET, null, responseType);
        }
        return restTemplate.exchange(clientUrl + PAGE + page + SIZE + size, HttpMethod.GET, null, responseType);
    }

    public ClientDTO createClient(ClientDTO client) {
        return restTemplate.postForObject(clientUrl, client, ClientDTO.class);
    }

}
