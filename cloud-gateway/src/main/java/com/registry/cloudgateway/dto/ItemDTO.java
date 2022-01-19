package com.registry.cloudgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private Integer id;

    private String serialNumber;
    private String catalogueNumber;
    private String name;
    private BigDecimal singlePrice;
    private BigDecimal vat;
    private Integer quantity;
    private BigDecimal totalPrice;

}
