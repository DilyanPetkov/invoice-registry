package com.registry.invoiceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriteriaDto {
    Integer quantityGreaterThan;
    BigDecimal totalPriceGreaterThan;
}
