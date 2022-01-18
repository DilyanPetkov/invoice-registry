package com.registry.invoiceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriteriaDto {
    Double quantityGreaterThan;
    Double totalPriceGreaterThan;
}
