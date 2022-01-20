package com.registry.clientservice.controller;

import com.registry.clientservice.dto.ClientDTO;
import com.registry.clientservice.exception.ClientAlreadyExistsException;
import com.registry.clientservice.exception.ClientNotFoundException;
import com.registry.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{clientNumber}")
    public ClientDTO getClientByClientNumber(@PathVariable("clientNumber") String clientNumber) {
        return clientService.findOneByClientNumber(clientNumber);
    }

    @GetMapping
    public Page<ClientDTO> getAllClients(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "size", required = false) Integer size) {
        return clientService.getAllClients(page, size);
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO client){
        return clientService.createClient(client);
    }

}
