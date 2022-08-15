package com.recordshop.clientservice.controller;

import com.recordshop.clientservice.dto.ClientRequest;
import com.recordshop.clientservice.dto.ClientResponse;
import com.recordshop.clientservice.service.ClientService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody ClientRequest clientRequest) {
        clientService.createClient(clientRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateClient(@RequestBody ClientRequest clientRequest) {
        clientService.updateClient(clientRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableClientById(@PathVariable Long id) {

        clientService.disableClientById(id);
    }

    @DeleteMapping("/doc/{documento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableClientByDocumento(@PathVariable String documento) {
        clientService.disableClientByDocumento(documento);
    }

    @GetMapping("/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean statusClientById(@PathVariable Long id) {
        return clientService.statusClientById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientResponse> getAllClients() { return clientService.getAllClients(); }

}
