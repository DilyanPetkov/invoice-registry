package com.registry.clientservice.controller;

import com.registry.clientservice.entity.Client;
import com.registry.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{clientNumber}")
    public Client getClientByClientNumber(@PathVariable("clientNumber") String clientNumber){
        return clientService.findOneByClientNumber(clientNumber);
    }

    @GetMapping
    public Page<Client> getAllClients(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "size", required = false) Integer size){
        return clientService.getAllClients(page, size);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client){
        return clientService.createClient(client);
    }

}
