package com.recordshop.clientservice.service;

import com.recordshop.clientservice.dto.ClientRequest;
import com.recordshop.clientservice.dto.ClientResponse;
import com.recordshop.clientservice.model.Client;
import com.recordshop.clientservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    public void createClient(ClientRequest clientRequest) {
        Client client = mapToClient(clientRequest);
        clientRepository.save(client);
        log.info("Client {} is saved", client.getId());
    }

    public void updateClient(ClientRequest clientRequest) {
        Client savedClient = getClient(clientRequest.getId());
        savedClient.setNomeCompleto(clientRequest.getNomeCompleto());
        savedClient.setDocumento(clientRequest.getDocumento());
        savedClient.setEmail(clientRequest.getEmail());
        savedClient.setDataNascimento(clientRequest.getDataNascimento());
        savedClient.setStatus(clientRequest.getStatus());
        savedClient.setTelefone(clientRequest.getTelefone());
        clientRepository.save(savedClient);
    }

    public void disableClientById(Long id) {
        Client savedClient = getClient(id);
        savedClient.setStatus(false);
        clientRepository.save(savedClient);
        log.info("Client ID: {} was disabled", id);
    }

    public void disableClientByDocumento(String documento) {
        Client savedClient = clientRepository.findByDocumento(documento)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find Client by Documento: %s", documento)));
        savedClient.setStatus(false);
        clientRepository.save(savedClient);
        log.info("Client Documento: {} was disabled", documento);
    }

    public List<ClientResponse> getAllClients() {
        List<Client> client = clientRepository.findAll();
        return client.stream().map(this::mapToClientResponse).toList();
    }
    private Client mapToClient(ClientRequest clientRequest) {
        Client client = Client.builder()
                .documento(clientRequest.getDocumento())
                .nomeCompleto(clientRequest.getNomeCompleto())
                .dataNascimento(clientRequest.getDataNascimento())
                .email(clientRequest.getEmail())
                .telefone(clientRequest.getTelefone())
                .status(clientRequest.getStatus())
                .build();
        return client;
    }

    private ClientResponse mapToClientResponse(Client client) {
        ClientResponse clientResponse = ClientResponse.builder()
                .id(client.getId())
                .documento(client.getDocumento())
                .nomeCompleto(client.getNomeCompleto())
                .dataNascimento(client.getDataNascimento())
                .email(client.getEmail())
                .telefone(client.getTelefone())
                .status(client.getStatus())
                .build();
        return clientResponse;
    }


    public Boolean statusClientById(Long id) {
        Client checkClient = getClient(id);
        return checkClient.getStatus();
    }

    private Client getClient(Long id) {
        Client checkClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find Client by ID %s", id)));
        return checkClient;
    }
}