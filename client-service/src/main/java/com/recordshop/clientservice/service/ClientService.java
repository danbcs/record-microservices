package com.recordshop.clientservice.service;

import com.recordshop.clientservice.dto.ClientRequest;
import com.recordshop.clientservice.dto.ClientResponse;
import com.recordshop.clientservice.model.Client;
import com.recordshop.clientservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    public void createClient(ClientRequest clientRequest) {
        Client client = convClient(clientRequest);
        clientRepository.save(client);
        log.info("Client {} is saved", client.getId());
    }

    public void updateClient(ClientRequest clientRequest) {
        Client savedClient = clientRepository.findById(clientRequest.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find Client by ID %s", clientRequest.getId())));
        savedClient.setNomeCompleto(clientRequest.getNomeCompleto());
        savedClient.setDocumento(clientRequest.getDocumento());
        savedClient.setEmail(clientRequest.getEmail());
        savedClient.setDataNascimento(clientRequest.getDataNascimento());
        savedClient.setStatus(clientRequest.getStatus());
        savedClient.setTelefone(clientRequest.getTelefone());
        clientRepository.save(savedClient);
    }

    public void disableClientById(Long id) {
        Client savedClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find Client by ID %s", id)));
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



    /*
    public void disableClientByDocumento(String documento) {
        List<Client> savedClient = clientRepository.findByDocumento(documento);
        if(savedClient.size() > 0) {
            log.info(" {} Client was disabled",
                    clientRepository.disableById(false, savedClient.get(0).getId()));
        } else {
            log.info("Document Client {}  was not find",
                    documento);
        }
    }*/

    public List<ClientResponse> getAllClients() {
        List<Client> client = clientRepository.findAll();
        return client.stream().map(this::convClient).toList();
    }
    private Client convClient(ClientRequest clientRequest) {
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

    private ClientResponse convClient(Client client) {
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


}