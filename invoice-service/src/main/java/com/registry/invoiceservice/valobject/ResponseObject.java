package com.registry.invoiceservice.valobject;

import com.registry.invoiceservice.entity.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseObject {
    private Invoice invoice;
    private Client client;
}
