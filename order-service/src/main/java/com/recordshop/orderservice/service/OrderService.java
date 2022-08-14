package com.recordshop.orderservice.service;

import com.recordshop.orderservice.dto.ClientDto;
import com.recordshop.orderservice.dto.DiskDto;
import com.recordshop.orderservice.dto.OrderRequest;
import com.recordshop.orderservice.model.Client;
import com.recordshop.orderservice.model.Disk;
import com.recordshop.orderservice.model.Order;
import com.recordshop.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setNumeroPedido(UUID.randomUUID().toString());
        order.setDataHora(new Date());
        Disk disk = mapToDto(orderRequest.getDiskDto());
        Client client = mapToDto(orderRequest.getClientDto());
        order.setDisk(disk);
        order.setClient(client);

        orderRepository.save(order);
    }

    private Client mapToDto(ClientDto clientDto) {
        Client client = new Client();
        client.setDocumento(clientDto.getDocumento());
        client.setNome(clientDto.getNome());
        return client;
    }

    private Disk mapToDto(DiskDto diskDto) {
        Disk disk = new Disk();
        disk.setNome(diskDto.getNome());
        disk.setQuantidade(diskDto.getQuantidade());
        return disk;
    }
}