package com.registry.clientservice.service;

import com.registry.clientservice.dto.ClientDTO;
import com.registry.clientservice.entity.Client;
import com.registry.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO findOneByClientNumber(String clientNumber) {
        return mapToDTO(clientRepository.findByClientNumber(clientNumber));
    }

    private List<ClientDTO> getClientDtoList(Page<Client> clientPage){
        List<Client> clients = clientPage.getContent();
        List<ClientDTO> invoiceDTOS = new ArrayList<>();
        for (Client client : clients) {
            invoiceDTOS.add(mapToDTO(client));
        }
        return  invoiceDTOS;
    }

    public Page<ClientDTO> getAllClients(Integer page, Integer size) {

        if (page == null || size == null) {
            if (BigInteger.valueOf(clientRepository.count()).compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) < 0) {
                Page<Client> clients = clientRepository.findAll(PageRequest.of(0, (int) clientRepository.count(),
                        Sort.by(Sort.Direction.ASC, "clientName")));
                List<ClientDTO> clientDTOS = getClientDtoList(clients);

                return new PageImpl<>(clientDTOS, PageRequest.of(0, (int) clientRepository.count(),
                        Sort.by(Sort.Direction.ASC, "clientName")),
                        (int) clientRepository.count());

            } else
                throw new ArithmeticException("Entities in DB way too much, cannot display them on the pages");
        }

        Page<Client> clients = clientRepository.findAll(PageRequest.of(page, size));
        List<ClientDTO> clientDTOS = getClientDtoList(clients);

        return new PageImpl<>(clientDTOS, PageRequest.of(page, size),
                (int) clientRepository.count());

    }

    public ClientDTO createClient(ClientDTO clientDTO) {
        return mapToDTO(clientRepository.save(mapToEntity(clientDTO)));
    }

    private Client mapToEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setClientName(clientDTO.getClientName());
        client.setClientNumber(clientDTO.getClientNumber());
        return client;
    }

    private ClientDTO mapToDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientNumber(client.getClientNumber());
        clientDTO.setClientName(client.getClientName());
        return clientDTO;
    }

}
