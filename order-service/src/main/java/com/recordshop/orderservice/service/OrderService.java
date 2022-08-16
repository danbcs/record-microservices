package com.recordshop.orderservice.service;

import com.recordshop.orderservice.dto.OrderRequest;
import com.recordshop.orderservice.dto.OrderResponse;
import com.recordshop.orderservice.model.Order;
import com.recordshop.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .idCliente(orderRequest.getIdCliente())
                .documentoCliente(orderRequest.getDocumentoCliente())
                .nomeCliente(orderRequest.getNomeCliente())
                .idDisco(orderRequest.getIdDisco())
                .nomeDisco(orderRequest.getNomeDisco())
                .quantidadeDisco(orderRequest.getQuantidadeDisco())
                .valorDisco(orderRequest.getValorDisco())
                .dataHora(new Date())
                .build();

        Boolean statusClient = webClientBuilder.build().get()
                .uri("http://client-service/api/client",
                        uriBuilder -> uriBuilder.path("/status/{id}")
                        .build(orderRequest.getIdCliente()))
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if(!statusClient) return "Client is Disabled";

        Integer qtdDisk = webClientBuilder.build().get()
                .uri("http://disk-service/api/disk/",
                        uriBuilder -> uriBuilder.path("qtd/{id}")
                                .build(orderRequest.getIdDisco()))
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
        if(qtdDisk == 0) return "Disk out of stock";

        orderRepository.save(order);
        String response = webClientBuilder.build().put()
                .uri("http://disk-service/api/disk/",
                        uriBuilder -> uriBuilder.path("/{id}/qtd/{quantidade}")
                                .build(orderRequest.getIdDisco(), qtdDisk-1))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return "Order Created ID: "+order.getId();

    }

    public List<OrderResponse> getAllOrders() {
        List<Order> order = orderRepository.findAll();
        return order.stream().map(this::mapToOrderResponse).toList();
    }

    private OrderResponse mapToOrderResponse(Order order) {
        OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .idCliente(order.getIdCliente())
                .documentoCliente(order.getDocumentoCliente())
                .nomeCliente(order.getNomeCliente())
                .idDisco(order.getIdDisco())
                .nomeDisco(order.getNomeDisco())
                .quantidadeDisco(order.getQuantidadeDisco())
                .valorDisco(order.getValorDisco())
                .dataHora(order.getDataHora())
                .build();
        return orderResponse;
    }

    public List<OrderResponse> getOrderByClient(Long idClient) {
        Optional<Order> checkOrder = orderRepository.findByIdClient(idClient);

        return checkOrder.stream().map(this::mapToOrderResponse).toList();


    }

    public List<OrderResponse> getOrderByPeriod(Date startDateApi, Date endDateApi) {

        Optional<Order> checkOrder = orderRepository.findByPeriod(startDateApi, endDateApi);

        return checkOrder.stream().map(this::mapToOrderResponse).toList();
    }
}