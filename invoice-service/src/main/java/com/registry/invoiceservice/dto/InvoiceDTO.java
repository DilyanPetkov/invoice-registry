package com.registry.invoiceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceDTO {
    private Long id;

    private String clientNumber;
    private String clientName;
    private String issuedBy;

    private List<ItemDTO> items;
}
