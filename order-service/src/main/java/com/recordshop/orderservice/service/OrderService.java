package com.recordshop.orderservice.service;

import com.recordshop.orderservice.dto.OrderRequest;
import com.recordshop.orderservice.dto.OrderResponse;
import com.recordshop.orderservice.model.Order;
import com.recordshop.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Long placeOrder(OrderRequest orderRequest) {
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
        orderRepository.save(order);
        
        return order.getId();
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