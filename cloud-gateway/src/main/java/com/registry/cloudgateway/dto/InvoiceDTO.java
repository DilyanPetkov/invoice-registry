package com.registry.cloudgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private Integer id;

    private String clientNumber;
    private String clientName;
    private String issuedBy;

    private List<ItemDTO> items;
}
