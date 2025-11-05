package com.turkcell.order_service.controller;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    // kafka ile iletişim kurmamızı sağlıyor
    private final StreamBridge streamBridge;

    public OrdersController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping()
    public String createOrder(@RequestBody CreateOrderDto dto) {
        // kafka eventi fırlat
        OrderCreatedEvent event = new OrderCreatedEvent(dto.productId());
        streamBridge.send("orderCreated-out-0", event);
        return dto.productId;
    }

    // mimari açıdan doğru değil örnek olması için burada
    record CreateOrderDto(String productId) {
    }

    record OrderCreatedEvent(String productId) {
    }

}
