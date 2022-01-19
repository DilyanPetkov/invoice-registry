package com.registry.clientservice.service;

import com.registry.clientservice.dto.ClientDTO;
import com.registry.clientservice.entity.Client;
import com.registry.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO findOneByClientNumber(String clientNumber) {
        return mapToDTO(clientRepository.findByClientNumber(clientNumber));
    }

    public Page<Client> getAllClients(Integer page, Integer size) {
        if (page == null || size == null) {
            if(BigInteger.valueOf(clientRepository.count()).compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) < 0){
                return clientRepository.findAll(PageRequest.of(0, (int) clientRepository.count(), Sort.by(Sort.Direction.ASC, "clientName")));
            }
            else throw new ArithmeticException("Entities in DB way too much, cannot display them on the pages");
        }
        return clientRepository.findAll(PageRequest.of(page, size));
    }

    public ClientDTO createClient(Client client) {
        return mapToDTO(clientRepository.save(client));
    }

    private ClientDTO mapToDTO(Client client){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientNumber(client.getClientNumber());
        clientDTO.setClientName(client.getClientName());
        return clientDTO;
    }

}
