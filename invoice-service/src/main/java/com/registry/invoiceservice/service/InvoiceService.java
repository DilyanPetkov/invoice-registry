package com.registry.invoiceservice.service;

import com.registry.invoiceservice.dto.CriteriaDto;
import com.registry.invoiceservice.dto.InvoiceDTO;
import com.registry.invoiceservice.dto.ItemDTO;
import com.registry.invoiceservice.entity.Invoice;
import com.registry.invoiceservice.entity.Item;
import com.registry.invoiceservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceDTO createInvoice(Invoice invoice) {
        List<Item> itemList = invoice.getItems();
        for (Item item : itemList) {
            item.setTotalPrice(item.getSinglePrice().add(BigDecimal.valueOf(item.getQuantity()).multiply(item.getVat())));
        }
        invoice.setItems(itemList);
        return mapToInvoiceDTO(invoiceRepository.save(invoice));
    }

    public Page<Invoice> getAllInvoices(Integer page, Integer size) {
        if (page == null || size == null) {
            return invoiceRepository.findAll(PageRequest.of(0, (int) invoiceRepository.count()));
        }
        return invoiceRepository.findAll(PageRequest.of(page, size));
    }

    public InvoiceDTO getInvoiceById(Long id) {
        return mapToInvoiceDTO(invoiceRepository.findById(id).get());
    }

    public List<InvoiceDTO> getInvoicesByCriteria(CriteriaDto criteriaDto) {
        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
        List<Invoice> invoices = invoiceRepository.searchInvoiceByCriteria(criteriaDto.getQuantityGreaterThan(), criteriaDto.getTotalPriceGreaterThan());
        for(Invoice invoice : invoices){
            invoiceDTOS.add(mapToInvoiceDTO(invoice));
        }
        return  invoiceDTOS;
    }

    private ItemDTO mapToItemDTO(Item item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(item.getName());
        itemDTO.setQuantity(item.getQuantity());
        itemDTO.setCatalogueNumber(item.getCatalogueNumber());
        itemDTO.setTotalPrice(item.getTotalPrice());
        itemDTO.setSerialNumber(item.getSerialNumber());
        itemDTO.setSinglePrice(item.getSinglePrice());
        itemDTO.setVat(item.getVat());
        return itemDTO;
    }

    private InvoiceDTO mapToInvoiceDTO(Invoice invoice){
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setClientName(invoice.getClientName());
        invoiceDTO.setClientNumber(invoice.getClientNumber());
        invoiceDTO.setIssuedBy(invoice.getIssuedBy());
        invoiceDTO.setId(invoice.getId());
        List<ItemDTO> itemDTOS = new ArrayList<>();
        List<Item> items = invoice.getItems();
        for(Item item : items){
            ItemDTO itemDTO = mapToItemDTO(item);
            itemDTOS.add(itemDTO);
        }
        invoiceDTO.setItems(itemDTOS);
        return invoiceDTO;
    }

}
