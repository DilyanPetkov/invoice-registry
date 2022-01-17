package com.registry.invoiceservice.repository;

import com.registry.invoiceservice.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Invoice findByClientNumber(String clientNumber);

    Page<Invoice> findAll(Pageable pageable);
}
