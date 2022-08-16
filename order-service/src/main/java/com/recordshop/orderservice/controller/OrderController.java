package com.recordshop.orderservice.controller;

import com.recordshop.orderservice.dto.OrderRequest;
import com.recordshop.orderservice.dto.OrderResponse;
import com.recordshop.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "order", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "order")
    @Retry(name = "order")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> "Por favor, realize o pedido novamente em alguns minutos");
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
