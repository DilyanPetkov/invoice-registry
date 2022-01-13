package com.registry.invoiceservice.service;

import com.registry.invoiceservice.entity.Invoice;
import com.registry.invoiceservice.entity.Item;
import com.registry.invoiceservice.repository.InvoiceRepository;
import com.registry.invoiceservice.valobject.Client;
import com.registry.invoiceservice.valobject.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Invoice createInvoice(Invoice invoice){
        List<Item> itemList = invoice.getItems();
        for(Item item: itemList){
            item.setTotalPrice(item.getSinglePrice() + item.getQuantity()*item.getVat());
        }
        invoice.setItems(itemList);
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Integer id){
        return invoiceRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public ResponseObject getInvoiceByClientNumber(String clientNumber) {
        ResponseObject responseObject = new ResponseObject();
        Invoice invoice = invoiceRepository.findByClientNumber(clientNumber);
        System.out.println("kurec " + invoice.getClientNumber());
        Client client = restTemplate.getForObject("http://localhost:8081/clients/"
                + invoice.getClientNumber(),Client.class);
        System.out.println("kurec 2" + client.getClientName());
        responseObject.setInvoice(invoice);
        responseObject.setClient(client);
        return responseObject;
    }
}
