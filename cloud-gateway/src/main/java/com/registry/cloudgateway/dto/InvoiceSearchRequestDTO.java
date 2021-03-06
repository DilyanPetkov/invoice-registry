package com.registry.cloudgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceSearchRequestDTO {
    Integer quantityGreaterThan;
    BigDecimal totalPriceGreaterThan;

}
