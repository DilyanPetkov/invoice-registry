package com.registry.invoiceservice.service;

import com.registry.invoiceservice.dto.InvoiceSearchRequestDTO;
import com.registry.invoiceservice.dto.InvoiceDTO;
import com.registry.invoiceservice.dto.ItemDTO;
import com.registry.invoiceservice.entity.Invoice;
import com.registry.invoiceservice.entity.Item;
import com.registry.invoiceservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        List<ItemDTO> itemList = invoiceDTO.getItems();
        for (ItemDTO itemDTO : itemList) {
            itemDTO.setTotalPrice(itemDTO.getSinglePrice().add(BigDecimal.valueOf(itemDTO.getQuantity()).multiply(itemDTO.getVat())));
        }
        invoiceDTO.setItems(itemList);
        return mapToInvoiceDTO(invoiceRepository.save(mapToInvoiceEntity(invoiceDTO)));
    }

    private List<InvoiceDTO> getInvoiceDtoList(Page<Invoice> invoicePage){
        List<Invoice> invoices = invoicePage.getContent();
        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
        for (Invoice invoice : invoices) {
            invoiceDTOS.add(mapToInvoiceDTO(invoice));
        }
        return  invoiceDTOS;
    }

    public Page<InvoiceDTO> getAllInvoices(Integer page, Integer size) {
        if (page == null || size == null) {
            if (BigInteger.valueOf(invoiceRepository.count()).compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) < 0) {
                Page<Invoice> invoicePage = invoiceRepository.findAll(PageRequest.of(0, (int) invoiceRepository.count(),
                        Sort.by(Sort.Direction.ASC, "clientName")));
                List<InvoiceDTO> invoiceDTOS = getInvoiceDtoList(invoicePage);

                return new PageImpl<>(invoiceDTOS, PageRequest.of(0, (int) invoiceRepository.count(),
                        Sort.by(Sort.Direction.ASC, "clientName")),
                        (int) invoiceRepository.count());

            } else
                throw new ArithmeticException("Entities in DB way too much, cannot display them on the pages");
        }

        Page<Invoice> invoicePage = invoiceRepository.findAll(PageRequest.of(page, size));
        List<InvoiceDTO> invoiceDTOS = getInvoiceDtoList(invoicePage);

        return new PageImpl<>(invoiceDTOS, PageRequest.of(page, size),
                (int) invoiceRepository.count());
    }

    public InvoiceDTO getInvoiceById(Long id) {
        return mapToInvoiceDTO(invoiceRepository.findById(id).get());
    }

    public List<InvoiceDTO> getInvoicesByCriteria(InvoiceSearchRequestDTO criteriaDto) {
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

    private Item mapToItemEntity(ItemDTO itemDto){
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setQuantity(itemDto.getQuantity());
        item.setCatalogueNumber(itemDto.getCatalogueNumber());
        item.setTotalPrice(itemDto.getTotalPrice());
        item.setSerialNumber(itemDto.getSerialNumber());
        item.setSinglePrice(itemDto.getSinglePrice());
        item.setVat(itemDto.getVat());
        return item;
    }

    private Invoice mapToInvoiceEntity(InvoiceDTO invoiceDto){
        Invoice invoice = new Invoice();
        invoice.setClientName(invoiceDto.getClientName());
        invoice.setClientNumber(invoiceDto.getClientNumber());
        invoice.setIssuedBy(invoiceDto.getIssuedBy());
        invoice.setId(invoiceDto.getId());
        List<Item> items = new ArrayList<>();
        List<ItemDTO> itemDTOS = invoiceDto.getItems();
        for(ItemDTO itemDTO : itemDTOS){
            Item item = mapToItemEntity(itemDTO);
            items.add(item);
        }
        invoice.setItems(items);
        return invoice;
    }

}
