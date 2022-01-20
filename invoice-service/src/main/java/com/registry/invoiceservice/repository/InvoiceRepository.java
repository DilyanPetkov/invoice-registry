package com.registry.invoiceservice.repository;

import com.registry.invoiceservice.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByClientNumber(String clientNumber);

    @Query(value = "SELECT * FROM invoice left join invoice_items on invoice.id=invoice_items.invoice_id left join item on invoice_items.items_id=item.id WHERE item.quantity < ?1 and item.total_price < ?2",
            nativeQuery = true)
    List<Invoice> searchInvoiceByCriteria(Integer quantity, BigDecimal totalPrice);

    Page<Invoice> findAll(Pageable pageable);
}
