package com.registry.invoiceservice.service;

import com.registry.invoiceservice.entity.Invoice;
import com.registry.invoiceservice.entity.Item;
import com.registry.invoiceservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(Invoice invoice){
        List<Item> itemList = invoice.getItems();
        for(Item item: itemList){
            item.setTotalPrice(item.getSinglePrice() + item.getQuantity()*item.getVat());
        }
        invoice.setItems(itemList);
        return invoiceRepository.save(invoice);
    }

    public Page<Invoice> getAllInvoices(Integer page, Integer size){
        if(page == null || size == null ){
            return invoiceRepository.findAll(PageRequest.of(0, (int) invoiceRepository.count()));
        }
        return invoiceRepository.findAll(PageRequest.of(page, size));
    }

    public Invoice getInvoiceById(Integer id){
        return invoiceRepository.findById(id).orElseThrow(NullPointerException::new);
    }

}
