package com.registry.clientservice.repository;

import com.registry.clientservice.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByClientNumber(String clientNumber);

    Page<Client> findAll(Pageable pageable);
}
