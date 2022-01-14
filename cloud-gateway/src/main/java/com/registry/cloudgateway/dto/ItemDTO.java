package com.registry.cloudgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private Integer id;

    private String serialNumber;
    private String catalogueNumber;
    private String name;
    private Double singlePrice;
    private Double vat;
    private Double quantity;
    private Double totalPrice;

}
