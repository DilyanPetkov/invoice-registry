package com.registry.clientservice.controller;

import com.registry.clientservice.dto.ClientDTO;
import com.registry.clientservice.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{clientNumber}")
    public ClientDTO getClientByClientNumber(@PathVariable("clientNumber") String clientNumber) {
        log.info("Inside getClientByClientNumber method");
        return clientService.findOneByClientNumber(clientNumber);
    }

    @GetMapping
    public Page<ClientDTO> getAllClients(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "size", required = false) Integer size) {
        log.info("Inside getAllClients method");
        return clientService.getAllClients(page, size);
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO client){
        log.info("Inside createClient method");
        return clientService.createClient(client);
    }

}
