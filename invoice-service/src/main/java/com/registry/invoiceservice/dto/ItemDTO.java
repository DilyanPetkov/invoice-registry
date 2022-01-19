package com.registry.invoiceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {

    private String serialNumber;
    private String catalogueNumber;
    private String name;
    private BigDecimal singlePrice;
    private BigDecimal vat;
    private BigDecimal quantity;
    private BigDecimal totalPrice;

}
