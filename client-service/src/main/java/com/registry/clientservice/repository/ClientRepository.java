package com.registry.clientservice.repository;

import com.registry.clientservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByClientNumber(String clientNumber);
}
