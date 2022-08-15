package com.recordshop.orderservice.controller;

import com.recordshop.orderservice.dto.OrderRequest;
import com.recordshop.orderservice.dto.OrderResponse;
import com.recordshop.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/find/client/{idClient}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrderByClient(@PathVariable Long idClient){
        return orderService.getOrderByClient(idClient);
    }

    @GetMapping("/find/period/{startDate}/{endDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrderByPeriod(
            @PathVariable
            @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date startDate,
            @PathVariable
            @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date endDate){
        return orderService.getOrderByPeriod(startDate, endDate);
    }

}
